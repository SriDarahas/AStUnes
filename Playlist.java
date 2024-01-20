//Name: Sri Darahas Koneru
//Description: This is the playlist file. There we have methods we can use that implement a Linked List.

public class Playlist {
    String name;
    Song first;
    public Playlist(){
        name = "library";
        first = Song.END;
    }

    public Playlist(String name) {
        this.name = name;
        first = Song.END;
    }

    public String getName() {
        return name;
    }

    //Adds a song to the playlist.
    public void add(Song song){
    	//BASE CASE 1: there are so songs in the playlist.
    	if(first.equals(Song.END)) {
    		first=song;
    	}
    	//BASE CASE 2: There are songs in the playlist.
    	else {
    		//CREAtes a object called sng, which we can use to circulate throughout the Linked List.
    		Song sng = first;
    		//CHECKS TO SEE how many songs are in the playlist and adds when there is so song next.
    		do {
    		    if (sng.next == Song.END) {
    		        sng.next = song;
    		        break;
    		    }
    		    sng = sng.next;
    		} while (true);

    	}
    }

   //RETURNS THE SIZE of the playlist.
    public int size() {
    	//STARTS with an empty size vARIABLE.
    	int size = 0;
    	Song sng = first;
    	//ADDS 1 to size everytime there exists a song.
    	do {
    	    size++;
    	    sng = sng.next;
    	} while (!sng.equals(Song.END));
    	return size;
    }

    //REMOVES THE first song from the playlist.
    public Song removeFirst() {
    	//CHECKS if there are songs in the playlist.
    	if(first.equals(Song.END)) {
    		return Song.END;
    	}
    	//Creates song that will store the first song.
    	Song r= first;
    	//replaces first with the next song.
    	first=first.next;
        return r;
    }
 
    //Removes the given song and returns it position.
    public int remove(Song song) {
    	//BASE CASE 1: There are no songs in the playlist.
    	if(first.equals(Song.END)) {
    		return -1;
    	}
    	//BASE CASE 2: The first song is the song to remove.
    	if(first.equals(song)) {
        first=first.next;
        return 0;
    	}
    	//ELSE: runs through each song and checks if each song is equal to song. If not equal, adds one to ps and moves on the next.
    	Song sng=first;
    	int ps=0;
    	while(!sng.next.equals(Song.END)) {
    		ps++;
    		if(sng.next.equals(song)) {
    			//Sets the next value to the value to the next value's next value.
    			sng.next=sng.next.next;
    			return ps;
    		}
    		sng=sng.next;
    	}return -1;
    }

    public Song head() {
        return first;
    }

    //FINDS the posiiton of a Song.
    public int getPosition(Song song) {
    	//Create a position variable to use.
    	int ps=0;
    	Song sng;
    	//INCREASES PS everytime the song is not equal to sng.
    	for (sng = first; sng != Song.END;ps++,sng = sng.next) {
    	    if (sng.equals(song)) {
    	        // If the song is found, return its position
    	        return ps;
    	    }
    	}
    	// If the song is not found, return -1
    	return -1;
    }

    public String listSongs() {
    	//SEES IF THERE ARE SONGS IN THE PLAYLIST.
    	if(first.equals(Song.END)) {
    		return "No songs in playlist.";
    	}
       //Create an empty string and add the sng.toString to it.
        String als = "";
        Song sng=first;
        while(!sng.equals(Song.END)) {
        	als = als + sng.toString() + "\n";
        	sng=sng.next;
        }
        
        return als + "\nTotal songs: " + this.size() + "."; 
    }
}