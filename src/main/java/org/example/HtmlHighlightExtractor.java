package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlHighlightExtractor {

    public static void main(String[] args) {
        String url = "https://study4.com/tests/4590/results/12784951/details/"; // Replace with the actual URL
        String filePath = "src/main/resources/highlighted_text.txt"; // Change this path

        try {
            String htmlContent = downloadHtml(url);
            String[] highlightedText = extractHighlightedText(htmlContent);
            saveToFile(highlightedText, filePath);
            System.out.println("Highlighted text has been saved to 'highlighted_text.txt'");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String downloadHtml(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.html();
    }

    public static String[] extractHighlightedText(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        Elements highlightedSpans = doc.select("span.highlight"); // Assuming highlights are marked with class 'highlight'
        String[] highlightedText = new String[highlightedSpans.size()];
        int i = 0;
        for (Element span : highlightedSpans) {
            highlightedText[i++] = span.text();
        }
        return highlightedText;
    }

    public static void saveToFile(String[] data, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }
        }
    }
}

