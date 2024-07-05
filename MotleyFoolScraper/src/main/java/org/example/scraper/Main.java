package org.example.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "https://www.fool.ca/";

        try {
            Document document = Jsoup.connect(url).get();
            Element bodyOfTheDocument = document.body();

            recurse(bodyOfTheDocument);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void recurse(Element e) {
        if(e.childrenSize() == 0) {
            System.out.println();
            System.out.println(e);
            return;
        }

        Elements childrenOfElement = e.children();
        for(Element innerE : childrenOfElement) {
            if(innerE.text().toLowerCase().contains("canadians")) recurse(innerE);
        }
    }
}