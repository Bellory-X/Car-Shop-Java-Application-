package service;

import java.io.IOException;
import java.text.ParseException;

public interface Service {
    void addRecord() throws IOException, ParseException;
    void deleteRecord() throws IOException, ParseException;
    void showRecord();
    void changeRecord() throws IOException, ParseException;
    void closeSession();
}
