package com.github.wildsource;

import java.io.Serializable;

public record Coordinate(String name, int x, int y, int z) implements Serializable {}