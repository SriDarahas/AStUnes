
//Name: Sri Darahas Koneru
//Description: This is the file for Main. Main.java is already implemented. Here we provide an overview on how AStUnes works. When you start up the program a playlist is already created called library. It currently has no songs in it.

import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {


    private static Song parseSong(String[] input) {
        String title = "";
        int i = 0;
        for (; i < input.length; i++) {
            if (input[i+1].equals("by")) {
                title += input[i];
                continue;
            } else if (input[i].equals("by")) {
                i++;
                break;
            }
            title += input[i] + " ";
        }
        String artist = "";
        for (; i < input.length-1; i++) {
            artist += input[i] + " ";
        }
        artist += input[input.length-1];
        Song song = new Song(title, artist);
        return song;
    }

    private static Playlist findPlaylist(String name, 
            ArrayList<Playlist> playlists) {
        // A hashmap would be handy here...
        // but for simplicity we will refrain from using it.
        for (Playlist p : playlists) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        // No playlist found.
        return null;
    }

    public static void main(String[] args) {

        // Probably makes more sense to use a hashmap, but let's not complica-
        // e things more than needed.
        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist());

        // Keep track of the current playlist.
        Playlist currentPlaylist = playlists.get(0);

        // Print out the menu
        System.out.println("Welcome to AStUnes!");
        System.out.println("===================");
        printMenu();

        // Create a BufferedReader object to read input from a keyboard
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(isr);
        String line = "";
        do {
            try {
                line = stdin.readLine();
                String[] input = line.split(" ");
                Song song = null;
                switch (input[0]) {
                    case "add":   // Add to current playlist
                        song = parseSong(
                                Arrays.copyOfRange(input, 1, input.length));
                        currentPlaylist.add(song);
                        System.out.printf("Successfully added %s\n", song);
                        break;
                    case "new":
                        Playlist playlist = new Playlist(input[1]);
                        playlists.add(playlist);
                        System.out.printf("Creating playlist %s\n", input[1]);
                        currentPlaylist = playlist;
                        System.out.printf("Switched to %s\n", input[1]);
                        break;
                    case "count":
                        System.out.printf(
                                "The current playlist %s has %d songs.\n", 
                                currentPlaylist.getName(), currentPlaylist.size()
                                );
                        break;
                    case "position":
                        song = parseSong(
                                Arrays.copyOfRange(input, 1, input.length));
                        int result = currentPlaylist.getPosition(song);
                        if(result == -1) {
                            System.out.printf("%s is not in the playlist.\n", song);
                        }
                        else {
                            System.out.printf(
                                    "%s song is located at position %d in %s\n", 
                                    song, result+1, currentPlaylist.getName());
                        }
                        break;
                    case "remove":
                        song = parseSong(
                                Arrays.copyOfRange(input, 1, input.length)
                                );
                        int index = currentPlaylist.remove(song);
                        if(index == -1) {
                            System.out.println("Song not found.\n");
                        } else {
                            System.out.printf(
                                    "Removed %s (at position %d) from %s.\n", 
                                    song, index+1, currentPlaylist.getName()
                                    );
                        }
                        break;
                    case "switch":
                        Playlist prev = currentPlaylist;
                        currentPlaylist = findPlaylist(input[1], playlists);
                        System.out.printf(
                                "Switched from playist %s to playlist %s",
                                prev.getName(), currentPlaylist.getName()
                                );
                        break;
                    case "pop":
                        // Remove current song, switch to next song in playlist.
                        if (currentPlaylist.head() == Song.END) {
                            System.out.printf(
                                    "No songs currently in playlist %s.\n", 
                                    currentPlaylist.getName()
                                    );
                            break;
                        }
                        Song current = currentPlaylist.removeFirst();
                        if (currentPlaylist.head() == Song.END) {
                            System.out.printf(
                                    "Finished %s.\n" +
                                    "No songs currently in playlist %s.\n",
                                    current, currentPlaylist.getName()
                                    );
                        }
                        else {
                            System.out.printf("Finished: %s.\nNow playing: %s\n", 
                                    current, currentPlaylist.head()
                                    );
                        }
                        break;
                    case "songs":
                        System.out.println("\n" + currentPlaylist.listSongs());
                        break;
                    case "playlists":
                        if (playlists.size() == 0) {
                            System.out.println("No playlists found.");
                        } else {
                            for (Playlist p : playlists) {
                                System.out.println(p.getName());
                            }
                        }
                        break;
                    case "help":
                        printMenu();
                        break;
                    case "quit": // Need to prevent default form executing.
                        break;
                    default:
                        System.out.print("Unknown action\n");
                        break;
                }
            } catch(IOException e) {
                System.out.println("Error: Could not parse line.");
            }
        } while(!line.equals("quit"));
    }
    private static void printMenu() {
        System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "add\t\tAdds a song to the current playlist\n" +
                "count\t\tCounts the number of songs in a playlist\n" +
                "position\tGets the position of a song in a playlist\n" +
                "remove\t\tRemove a song from the playlist\n" +
                "new\t\t\tCreate a new playlist\n" +
                "switch\t\tSwitch to another playlist\n" +
                "pop\t\t\tFinish and remove the first song from the playlist\n" +
                "songs\t\tList the songs in the current playlist\n" +
                "playlists\tList the playlists\n"  +
                "quit\t\tQuit the program\n" +
                "help\t\tDisplay Help\n\n");
    }
}