#include <string>
#include <iostream>

class Artist{
    public:
        std::string name;
        Artist(std::string name): name(name) {}
        Artist(Artist const &artist): name(artist.name) {}
        void change_name(const std::string str){
            name = str;
        }
};

class Song {
    public:
        Artist* artist;
        Song(Artist* artist): artist(artist) { }
        //deep copy constructor
        Song(Song const &song): artist(new Artist(*song.artist)) {}
};

int main(){
    Artist *art1 = new Artist("IU");
    Song rollin(art1);
    Song lilac(rollin);
    rollin.artist -> change_name("Brave girls");
    std::cout << rollin.artist->name << std::endl;
    std::cout << lilac.artist -> name << std::endl;
}