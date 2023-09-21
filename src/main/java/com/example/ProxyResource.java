package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@Path("/proxy")
public class ProxyResource {

    private static final String TARGET_SITE = "https://quarkus.io/";

    @GET
    @Path("/{path : .+}")
    @Produces(MediaType.TEXT_HTML)
    public String proxy(@PathParam("path") String path) throws IOException {

        // Fetch the content from the target website
        Document doc = Jsoup.connect(TARGET_SITE + path).get();

        // Modify the text of 6-letter words by adding "™"
        Elements elements = doc.body().getAllElements();
        for (Element element : elements) {
            String[] words = element.text().split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() == 6) {
                    words[i] += "™";
                }
            }
            element.text(String.join(" ", words));
        }

        // Modify internal navigation links
        Elements links = doc.select("a[href^='/']");
        for (Element link : links) {
            String href = link.attr("href");
            link.attr("href", "/proxy" + href);
        }

        return doc.html();
    }
}

