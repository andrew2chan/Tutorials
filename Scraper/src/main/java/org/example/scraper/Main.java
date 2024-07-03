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
        String url = "https://books.toscrape.com/";

        try {
            Document document = Jsoup.connect(url).get();
            Elements books = document.select("article.product_pod");

            for(Element bk: books) {
                String title = bk.select("h3 > a").text();
                String price = bk.select(".product_price > .price_color").text();

                System.out.println("title: " + title + " price: " + price);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}