# Recursive Algorithms applied to Computer Graphics

## Description

This program consists of two recursive algorithms, Koch Curve and Flood Fill. After reading an input text, it returns an image.

## Visuals

Some of images generated using those recursive algorithms

<img src="https://i.imgur.com/dhRUvBg.png" width="400px" heigth="400px" />

<img src="https://i.imgur.com/DWlBgne.png" width="400px" heigth="400px" />

<img src="https://i.imgur.com/dM05VIB.png" width="400px" heigth="400px" />

## Usage

The program must receive an input file with the commands to create the image. The following commands are available:

> **SET_PIXEL** : Receive a coordinate pair (x,y) and color that pixel.
> **SET_COLOR**: Receive a RGB configuration and defines the color that SET_PIXEL will use.
> **DRAW_LINE**: Receive two coordinate pairs (x1,y1),(x2,y2) and draw through two points.
> **KOCH_CURVE**: Receive two coordinate pairs and a threshold number, that define the minimum length a line can be.
> **REGION_FILL**: Receive a coordinate point and begin to color every point around it, until it finds a boundary.

For example, the first image is generated by the following *.txt* file:

```txt
SET_COLOR 0 0 255
KOCH_CURVE 148 1024 1900 1024 5
KOCH_CURVE 1900 1024 148 1024 5
SET_COLOR 210 124 34
REGION_FILL 100 100
```

To compile and run it, navigate to the **src** folder on the command line and type:

``` java
javac Main.java
java -Xss200M Main [path to input file] [name of output image].png 
```

The **-Xss200M** term alters the java call stack memory from **1M** to **200M** to run the program. The region fill algorithm, consumes a lot of memory, so if you have problems with stackOverFlow try to use a higher call stack size.
