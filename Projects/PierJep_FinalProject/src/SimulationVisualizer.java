import java.awt.*;
import java.util.List;
import javax.swing.*;

import Skeleton.SimulationInput;
import Simulation.LightColor;
import Simulation.Vehicle;

public class SimulationVisualizer extends JFrame {
    JPanel roadPanel = new JPanel();

    public SimulationVisualizer(SimulationInput input) {
        // Set up the JFrame
        setTitle("Simulation Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components to represent simulation elements
        roadPanel.setBackground(Color.GRAY);
        roadPanel.setLayout(new BorderLayout()); // Change layout manager to BorderLayout

        // Add roadPanel to the JFrame
        add(roadPanel);

        // Make the JFrame visible
        setVisible(true);

        // Update visualization to draw road and vehicles
        // updateVisualization(List.of(), LightColor.GREEN); // Initially, no vehicles and green traffic light
    }

    // Method to update visualization based on simulation state
    public void updateVisualization(List<Vehicle> vehicles, LightColor currentTrafficLightColor) {
        // Clear the roadPanel before updating to avoid overlapping drawings
        roadPanel.removeAll();

        // Draw the road segment
        drawRoad();

        // Draw vehicles
        drawVehicles(vehicles);

        // Draw traffic lights
        drawTrafficLight(currentTrafficLightColor);

        // Repaint the roadPanel to update the visualization
        roadPanel.revalidate();
        roadPanel.repaint();
    }

    // Method to draw the road segment
    private void drawRoad() {
        roadPanel.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // Set the color of the road
                g2d.setColor(Color.RED);

                // Create a rectangle representing the road
                Rectangle roadRect = new Rectangle(50, 80, 500, 20); // Adjust the dimensions as needed

                // Draw the road
                g2d.fill(roadRect);

                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(roadPanel.getWidth(), roadPanel.getHeight());
            }
        }, BorderLayout.CENTER); // Add road to the center of roadPanel
    }

    private void drawVehicles(List<Vehicle> vehicles) {
        // Iterate over vehicles and draw each vehicle
        for (int i = 0; i < vehicles.size(); i++) {
            // Create a rectangle representing the vehicle
            Vehicle vehicle = vehicles.get(i);
            int vehiclePosition = vehicle.getPosition();
            int vehicleXCoordinate = 50 + vehiclePosition; // Adjust the X-coordinate based on vehicle position
            int vehicleYCoordinate = 80 + (i * 30); // Adjust the spacing between vehicles
            Rectangle vehicleRect = new Rectangle(vehicleXCoordinate, vehicleYCoordinate, 20, 10); // Adjust the dimensions as needed

            // Set the color of the vehicle (e.g., blue)
            Color vehicleColor = Color.BLUE;

            // Draw the rectangle directly onto the roadPanel
            JPanel vehiclePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();

                    // Set the color of the vehicle
                    g2d.setColor(vehicleColor);

                    // Draw the vehicle rectangle
                    g2d.fill(vehicleRect);

                    g2d.dispose();
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(vehicleRect.width, vehicleRect.height);
                }
            };

            // Set the position of the vehicle panel
            vehiclePanel.setBounds(vehicleRect);

            // Add the vehicle panel to the roadPanel
            roadPanel.add(vehiclePanel);
        }
    }

    private void drawTrafficLight(LightColor currentTrafficLightColor) {
        // Create a JPanel to hold the traffic light
        JPanel trafficLightPanel = new JPanel();
        trafficLightPanel.setLayout(new GridLayout(3, 1)); // Three lights vertically arranged

        // Create three colored circles representing the traffic lights
        int circleSize = 20; // Adjust size as needed
        JPanel redLight = createColoredPanel(currentTrafficLightColor == LightColor.RED ? Color.RED : Color.BLACK, circleSize);
        JPanel yellowLight = createColoredPanel(currentTrafficLightColor == LightColor.YELLOW ? Color.YELLOW : Color.BLACK, circleSize);
        JPanel greenLight = createColoredPanel(currentTrafficLightColor == LightColor.GREEN ? Color.GREEN : Color.BLACK, circleSize);

        // Add colored panels to trafficLightPanel
        trafficLightPanel.add(redLight);
        trafficLightPanel.add(yellowLight);
        trafficLightPanel.add(greenLight);

        // Add trafficLightPanel to the roadPanel
        roadPanel.add(trafficLightPanel, BorderLayout.NORTH); // Add traffic light panel to the top of the roadPanel
    }

    // Helper method to create a JPanel with a colored circle
    private JPanel createColoredPanel(Color color, int size) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // Set the color of the circle
                g2d.setColor(color);

                // Draw the circle
                g2d.fillOval(0, 0, size, size);

                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(size, size);
            }
        };

        return panel;
    }

}

