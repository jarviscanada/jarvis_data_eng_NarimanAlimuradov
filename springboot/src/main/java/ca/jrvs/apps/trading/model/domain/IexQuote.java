package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "primaryExchange",
        "calculationPrice",
        "open",
        "openTime",
        "openSource",
        "close",
        "closeTime",
        "closeSource",
        "high",
        "highTime",
        "highSource",
        "low",
        "lowTime",
        "lowSource",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "volume",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "iexOpen",
        "iexOpenTime",
        "iexClose",
        "iexCloseTime",
        "marketCap",
        "peRatio",
        "week52High",
        "week52Low",
        "ytdChange",
        "lastTradeTime",
        "isUSMarketOpen"
})
public class IexQuote {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private double open;
    @JsonProperty("openTime")
    private long openTime;
    @JsonProperty("openSource")
    private String openSource;
    @JsonProperty("close")
    private double close;
    @JsonProperty("closeTime")
    private long closeTime;
    @JsonProperty("closeSource")
    private String closeSource;
    @JsonProperty("high")
    private double high;
    @JsonProperty("highTime")
    private long highTime;
    @JsonProperty("highSource")
    private String highSource;
    @JsonProperty("low")
    private double low;
    @JsonProperty("lowTime")
    private long lowTime;
    @JsonProperty("lowSource")
    private String lowSource;
    @JsonProperty("latestPrice")
    private double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private long latestUpdate;
    @JsonProperty("latestVolume")
    private long latestVolume;
    @JsonProperty("iexRealtimePrice")
    private double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private long iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private long delayedPriceTime;
    @JsonProperty("oddLotDelayedPrice")
    private double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private long oddLotDelayedPriceTime;
    @JsonProperty("extendedPrice")
    private double extendedPrice;
    @JsonProperty("extendedChange")
    private double extendedChange;
    @JsonProperty("extendedChangePercent")
    private double extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private long extendedPriceTime;
    @JsonProperty("previousClose")
    private double previousClose;
    @JsonProperty("previousVolume")
    private long previousVolume;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private double changePercent;
    @JsonProperty("volume")
    private long volume;
    @JsonProperty("iexMarketPercent")
    private double iexMarketPercent;
    @JsonProperty("iexVolume")
    private long iexVolume;
    @JsonProperty("avgTotalVolume")
    private long avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private long iexBidPrice;
    @JsonProperty("iexBidSize")
    private long iexBidSize;
    @JsonProperty("iexAskPrice")
    private long iexAskPrice;
    @JsonProperty("iexAskSize")
    private long iexAskSize;
    @JsonProperty("iexOpen")
    private double iexOpen;
    @JsonProperty("iexOpenTime")
    private long iexOpenTime;
    @JsonProperty("iexClose")
    private double iexClose;
    @JsonProperty("iexCloseTime")
    private long iexCloseTime;
    @JsonProperty("marketCap")
    private long marketCap;
    @JsonProperty("peRatio")
    private double peRatio;
    @JsonProperty("week52High")
    private double week52High;
    @JsonProperty("week52Low")
    private double week52Low;
    @JsonProperty("ytdChange")
    private double ytdChange;
    @JsonProperty("lastTradeTime")
    private long lastTradeTime;
    @JsonProperty("isUSMarketOpen")
    private boolean isUSMarketOpen;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public String getOpenSource() {
        return openSource;
    }

    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseSource() {
        return closeSource;
    }

    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public long getHighTime() {
        return highTime;
    }

    public void setHighTime(long highTime) {
        this.highTime = highTime;
    }

    public String getHighSource() {
        return highSource;
    }

    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getLowTime() {
        return lowTime;
    }

    public void setLowTime(long lowTime) {
        this.lowTime = lowTime;
    }

    public String getLowSource() {
        return lowSource;
    }

    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public long getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(long latestVolume) {
        this.latestVolume = latestVolume;
    }

    public double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public long getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(long iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public double getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    public void setOddLotDelayedPrice(double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    public long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    public void setOddLotDelayedPriceTime(long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public double getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public long getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(long previousVolume) {
        this.previousVolume = previousVolume;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public long getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(long iexVolume) {
        this.iexVolume = iexVolume;
    }

    public long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public long getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(long iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public long getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(long iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public long getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(long iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public long getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(long iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public double getIexOpen() {
        return iexOpen;
    }

    public void setIexOpen(double iexOpen) {
        this.iexOpen = iexOpen;
    }

    public long getIexOpenTime() {
        return iexOpenTime;
    }

    public void setIexOpenTime(long iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    public double getIexClose() {
        return iexClose;
    }

    public void setIexClose(double iexClose) {
        this.iexClose = iexClose;
    }

    public long getIexCloseTime() {
        return iexCloseTime;
    }

    public void setIexCloseTime(long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    public long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public boolean isUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setUSMarketOpen(boolean USMarketOpen) {
        isUSMarketOpen = USMarketOpen;
    }
}
