package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {

    public boolean matchJpeg(String filename) {
        if (filename.toLowerCase().matches("^.*\\.(jpg|jpeg)$")) {
            return true;
        }
        return false;
    }

    public boolean matchIp(String ip) {
        if (ip.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {
            return true;
        }
        return false;
    }

    public boolean isEmptyLine(String line) {
        if (line.matches("^\\s*$")) {
            return true;
        }
        return false;
    }
}