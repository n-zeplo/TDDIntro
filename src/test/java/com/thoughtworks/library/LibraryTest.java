package com.thoughtworks.library;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

public class LibraryTest {
    private List<String> books;
    private PrintStream printStream;
    private DateTime time;
    private DateTimeFormatter dateTimeFormatter;

    /*

        List books tests. Implement the first three tests for the Verify exercise

     */


    @Before
    public void setUp() throws Exception {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        time = new DateTime();
        dateTimeFormatter = mock(DateTimeFormatter.class);
    }

    private void generateLibraryAndListBooks() {
        Library library = new Library(books, printStream, null);
        library.listBooks();
    }

    private void createLibraryAndCallWelcome(DateTime time, DateTimeFormatter dateTimeFormatter) {
        Library library = new Library(books, printStream, dateTimeFormatter);

        library.welcome(time);
    }

    @Test
    public void shouldPrintBookTitleWhenThereIsOneBook() {
        String title = "Book Title";
        books.add(title);
        generateLibraryAndListBooks();

        verify(printStream).println("Book Title");
    }

    @Test
    public void shouldPrintNothingWhenThereAreNoBooks() {
        generateLibraryAndListBooks();

        verify(printStream, never()).println();
    }

    @Test
    public void shouldPrintBothBookTitlesWhenThereAreTwoBooks() {
        String title1 = "Book Title 1";
        String title2 = "Book Title 2";
        books.add(title1);
        books.add(title2);
        generateLibraryAndListBooks();

        verify(printStream).println("Book Title 1");
        verify(printStream).println("Book Title 2");
    }

    /*

        Welcome message tests. Implement these tests for the when/thenReturn exercise

     */

    
    // This one is done for you
    @Test
    public void shouldWelcomeUser() {
        createLibraryAndCallWelcome(time, dateTimeFormatter);

        verify(printStream).println(contains("Welcome"));
    }
    
    @Test
    public void shouldDisplayFormattedTime() {
        when(dateTimeFormatter.print(time)).thenReturn("FormattedTimeString");

        createLibraryAndCallWelcome(time, dateTimeFormatter);

        verify(printStream).println(contains("FormattedTimeString"));
    }

    @Test
    public void shouldDisplayFormattedTimeWhenItIsAnEmptyString() {
        when(dateTimeFormatter.print(time)).thenReturn("");

        createLibraryAndCallWelcome(time, dateTimeFormatter);

        verify(printStream).println(contains(dateTimeFormatter.print(time)));
    }
}