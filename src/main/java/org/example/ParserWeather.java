package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParserWeather {
    private static File file = new File("src/main/resources/weather.json");
    public static List<DateWeather> dateWeathers = new ArrayList<>();
    private static Document getPage() throws IOException {
        String url = "https://world-weather.ru/pogoda/russia/ryazan/7days/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    /**
     * Преобразование списка обектов в json файл
     *
     * @param list
     * @return
     */
    private static String getJsonWeather(List<DateWeather> list) {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = "ok";
        try {
            objectMapper.writeValue(file, list);
        } catch ( IOException e ) {
            System.out.println("что-то пошло не так.");
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        Document page = getPage();
        Element allPage = page.select("div[id=content-left]").first();
        Elements days = allPage.select("div[class=weather-short]");

        for (Element elem : days) {
            String date1 = elem.select("div[class = dates short-d red]").text();
            String date = elem.select("div[class = dates short-d]").text();
            StringBuilder sb = new StringBuilder();

            System.out.print(sb.append(date1).append(date)
                    .append(" -   Погода   Температура     Давление     Ветер     Влажность").append("\n"));
            StringBuilder sb1 = new StringBuilder();

            //беру только данные времени суток - День
            String weather = elem.select("td[ class = weather-temperature]")
                    .attr("div", "title").get(2).child(0).attr("title");
            String temperature = elem.select("td[class = weather-temperature]").get(2).text();
            String pressure = elem.select("td[class = weather-pressure]").get(2).text();
            String wind = elem.select("td[ class = weather-wind]")
                    .attr("div", "title").get(2).child(0).attr("title");
            String humidity = elem.select("td[class = weather-humidity]").get(2).text();
            System.out.println(sb1.append("                     ").append(weather).append("     ")
                    .append(temperature).append("          ").append(pressure).append("     ")
                    .append(wind).append("     ").append(humidity).append("\n"));
            dateWeathers.add(new DateWeather(weather, temperature, pressure, wind, humidity));
        }
        for (DateWeather d : dateWeathers) {
            System.out.println(d);
        }
        System.out.println("Очищаем список.");
        getJsonWeather(dateWeathers);
        dateWeathers.clear();

        //чтение json  файла и запись данных в список обектов
        ObjectMapper objectMapper = new ObjectMapper();
        dateWeathers = objectMapper.readValue(file, new TypeReference<List<DateWeather>>() {
        });
        System.out.println(dateWeathers.size());
        for (DateWeather w : dateWeathers) {
            System.out.println(w);
        }
    }
}