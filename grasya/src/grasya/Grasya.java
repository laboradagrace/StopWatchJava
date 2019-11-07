/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grasya;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;


/**
/**
 *
 * @author 2ndyrGroupC
 */
public class Grasya extends javax.swing.JFrame implements Runnable {

        private javax.swing.JLabel Display;
        private javax.swing.JButton StartButton;
        private javax.swing.JButton StopButton;

        private final static java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat("mm : ss.SSS");
        private long elapsed;
        private long startTime;
        private Thread updater;
        private boolean isRunning = false;
        private Timer timer;

        public Grasya() {
            initComponents();
        }

        private void initComponents() {

            Display = new javax.swing.JLabel();
            StartButton = new javax.swing.JButton();
            StopButton = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            Display.setText("00 : 00.000");

            StartButton.setText("Start");
            StartButton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    StartButtonActionPerformed(evt);
                }
            });

            StopButton.setText("Stop");
            StopButton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    StopButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(131, 131, 131)
                                            .addComponent(StartButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(StopButton))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(165, 165, 165)
                                            .addComponent(Display)))
                            .addContainerGap(147, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(124, 124, 124)
                            .addComponent(Display)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(StartButton)
                                    .addComponent(StopButton))
                            .addContainerGap(133, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
        }

        private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {
            stopStopwatch();
        }

        private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {
            //startStopwatch();
            new Grasya(5);
        }

        public static void main(String args[]) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Grasya().setVisible(true);
                }
            });
        }

//STOPWATCH
        @Override
        public void run() {
            try {
                while (isRunning) {
                    SwingUtilities.invokeAndWait(displayUpdater);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }

        private final Runnable displayUpdater = new Runnable() {
            @Override
            public void run() {
                displayElapsedTime(System.currentTimeMillis() - Grasya.this.startTime);
            }
        };

        private void displayElapsedTime(long elapsedTime1) {
            Display.setText(timerFormat.format(new java.util.Date(elapsedTime1)));
        }

        public void startStopwatch() {
            System.out.println("2:Start stopwatch.");
            startTime = System.currentTimeMillis();
            isRunning = true;
            updater = new Thread(this);
            updater.start();
        }

        private void stopStopwatch() {
            System.out.println("3:Stop stopwatch.");
            elapsed = System.currentTimeMillis() - startTime;
            isRunning = false;
            try {
                updater.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            displayElapsedTime(elapsed);
        }

//TIMER
        public Grasya(int seconds) {
            initComponents();
            setVisible(true);
            timer = new Timer();
            timer.schedule(new Task(), seconds * 1000);
        }

        class Task extends TimerTask {

            @Override
            public void run() {
                System.out.println("1:Timer has finished.");
                timer.cancel();
                startStopwatch();
            }
        }
    }
