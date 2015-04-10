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

    /*

        List books tests. Implement the first three tests for the Verify exercise

     */


    @Before
    public void setUp() throws Exception {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
    }

    private static void generateLibraryAndListBooks(List<String> books, PrintStream printStream) {
        Library library = new Library(books, printStream, null);
        library.listBooks();
    }

    @Test
    public void shouldPrintBookTitleWhenThereIsOneBook() {
        String title = "Book Title";
        books.add(title);
        generateLibraryAndListBooks(books, printStream);

        verify(printStream).println("Book Title");
    }

    @Test
    public void shouldPrintNothingWhenThereAreNoBooks() {
        generateLibraryAndListBooks(books, printStream);

        verify(printStream, never()).println();
    }

    @Test
    public void shouldPrintBothBookTitlesWhenThereAreTwoBooks() {
        String title1 = "Book Title 1";
        String title2 = "Book Title 2";
        books.add(title1);
        books.add(title2);
        generateLibraryAndListBooks(books, printStream);

        verify(printStream).println("Book Title 1");
        verify(printStream).println("Book Title 2");
    }

    /*

        Welcome message tests. Implement these tests for the when/thenReturn exercise

     */

    
    // This one is done for you
    @Test
    public void shouldWelcomeUser() {
        DateTimeFormatter dateTimeFormatter = mock(DateTimeFormatter.class);
        Library library = new Library(books, printStream, dateTimeFormatter);

        // We don't need to mock DateTime because it is a value object
        // We can't mock it because it is a final class
        DateTime time = new DateTime();
        
        library.welcome(time);
        
        verify(printStream).println(contains("Welcome"));
    }
    
    @Test
    public void shouldDisplayFormattedTime() {
        DateTime time = new DateTime();
        DateTimeFormatter dateTimeFormatter = mock(DateTimeFormatter.class);

        when(dateTimeFormatter.print(time)).thenReturn("FormattedTimeString");

        Library library = new Library(books, printStream, dateTimeFormatter);

        library.welcome(time);

        verify(printStream).println(contains("FormattedTimeString"));
    }

    @Test
    public void shouldDisplayFormattedTimeWhenItIsAnEmptyString() {

        // implement me
    }
}