package org.example;

public class DateWeather {
    private String weather;
    private String temperature;
    private String pressure;
    private String wind;
    private String humidity;

    public DateWeather() {
    }

    public DateWeather(String weather, String temperature, String pressure, String wind, String humidity) {
        this.weather = weather;
        this.temperature = temperature;
        this.pressure = pressure;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "DateWeather{" +
                "weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pressure=" + pressure +
                ", wind='" + wind + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
