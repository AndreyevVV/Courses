package com.epam.rd.autocode.floodfill;

public interface FloodFill {
    void flood(final String map, final FloodLogger logger);


    static FloodFill getInstance() {
        FloodMe floodMe = new FloodMe();
        return floodMe;
    }

    char LAND = '█';
    char WATER = '░';
}
