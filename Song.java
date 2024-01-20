
//Assignment: 9
//Name: Sri Darahas Koneru
//StudentID: 1226423799
//Lecture: cse-205, Tu, Th 4.30 to 5.45
//Description: This is the file for song. The class Song represents a song in a playlist and has two instance variables

public class Song {
    private String title;
    private String artist;
    public Song next;
    public static final Song END = new Song();

    public Song(String title, String artist){
        this.title = title;
        this.artist = artist;
        next = END;
    }

    // This is used to construct the END Table.
    private Song() {
        title = "";
        artist = "";
        next = this;
    }

    public boolean equals(Song other) {
        if (this.title.equals(other.title) 
         && this.artist.equals(other.artist))
            return true;
        return false;
    }

    public String toString(){
        return "Title: " + title + "\tArtist: " + artist;
    }
}