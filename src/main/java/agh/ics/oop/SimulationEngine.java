package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    public MoveDirection[] directions;
    public IWorldMap map;
    public int animal_count;

    SimulationEngine(MoveDirection[] directions_, IWorldMap map_, Vector2d[] positions){
        directions = directions_;
        map = map_;
        int placed_animals = 0;
        for(Vector2d v : positions){
            if(map.place(new Animal(map, v)))
                placed_animals ++;
        }
        animal_count = placed_animals;
    }

    @Override
    public void run(){
        int count = 0;
        for(MoveDirection dir : directions) {
            System.out.println(map);
            ((Animal) map.getAnimal(count++)).move(dir);
        }
        System.out.println(map);
    }

//    public void visualize() throws InterruptedException {
//        // create frames / vars
//        JFrame f = new JFrame("Visualization");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.getContentPane().setBackground(Color.BLACK);
//        int[] size = map.getSize();
//        int width = size[0] + 2;
//        int height = size[1] + 2;
//        JLabel[] jLabels = new JLabel[width * height];
//
//        // create labels and number axis
//        for(int i=0; i < width*height; i++){
//            if(i % width == 0)
//                jLabels[i] = new JLabel(String.valueOf(height - 2 - i / width), JLabel.CENTER);
//            else if(i / width == height - 1)
//                jLabels[i] = new JLabel(String.valueOf(i % width - 1), JLabel.CENTER);
//            else
//                jLabels[i] = new JLabel("", JLabel.CENTER);
//            jLabels[i].setFont(new Font("Calibri", Font.BOLD, 20));
//            jLabels[i].setBackground(Color.WHITE);
//            jLabels[i].setOpaque(true);
//            f.add(jLabels[i]);
//        }
//        jLabels[width*height - width].setText("y/x");
//        f.setSize(width * 60,height * 60);
//        f.setLayout(new GridLayout(height, width, 10, 10));
//
//        // draw initial setup
//        for(int i=0; i<map.animalCount(); i++){
//            Animal animal = map.getAnimal(i);
//            Vector2d pos = animal.getPosition();
//            jLabels[pos.x + 1+ (height - 2 - pos.y) * width].setText("N");
//        }
//        f.setVisible(true);
//        TimeUnit.SECONDS.sleep(1);
//
//        // draw animal movements. delete prev cell and draw next one if movement was possible
//        int count = 0;
//        for(MoveDirection dir : directions){
//            Animal animal = map.getAnimal(count++);
//            Vector2d pos = animal.getPosition();
//            jLabels[pos.x + 1+ (height - 2 - pos.y) * width].setBackground(Color.red);
//            if(animal.move(dir)){
//                Vector2d new_pos = animal.getPosition();
//                jLabels[pos.x + 1+ (height - 2 - pos.y) * width].setText("");
//                jLabels[pos.x + 1+ (height - 2 - pos.y) * width].setBackground(Color.white);
//                jLabels[new_pos.x + 1+ (height - 2 - new_pos.y) * width].setBackground(Color.red);
//                jLabels[new_pos.x + 1+ (height - 2 - new_pos.y) * width].setText(animal.toString());
//                TimeUnit.MILLISECONDS.sleep(500);
//                jLabels[new_pos.x + 1+ (height - 2 - new_pos.y) * width].setBackground(Color.white);
//            }
//            else{
//                System.out.println("Collision detected. Cannot move.");
//                TimeUnit.MILLISECONDS.sleep(500);
//                jLabels[pos.x + 1+ (height - 2 - pos.y) * width].setBackground(Color.white);
//            }
//        }
//
//        System.out.println("Simulation Done.");
//    }
}
