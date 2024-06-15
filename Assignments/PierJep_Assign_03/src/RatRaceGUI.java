
/* CSC 121, 
 * Homework 2: Rat Race
 * 
 * Description: This file is the provided GUI for the 
 *              rat race assignment.  It should not be modified.
 */


import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


/** Runs program. */
public final class RatRaceGUI
{
  /** Creates and shows GUI. */
  public static void main(String[] args)
  {
	  new RatFrame().setVisible(true);
  }
}

/** Window frame to hold Rat Race GUI. */
final class RatFrame extends JFrame implements ActionListener, ChangeListener
{
  private static final int MAX_FPS = 100;
  
  // INSTANCE VARIABLES
  private int FPS = 5;
  private JLabel ratLabel = new JLabel();
  private JComboBox cbox;
  private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, MAX_FPS, FPS);
  private JFileChooser mazeChooser = new JFileChooser(System.getProperty("user.dir"));
  private Vector ratTypes = new Vector();
  private Class ratClass = null;
  private javax.swing.Timer timer = new javax.swing.Timer(1000 / FPS, this);
  private RatPanel ratpanel = new RatPanel();
  private Maze maze = null;

  /** Constructs new window. */
  public RatFrame()
  {
    // set properties of frame
    super("Rat Race");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    loadRatClasses();
    cbox = new JComboBox(ratTypes);
    try
    {
      ratClass = (Class)ratTypes.get(0);
      loadRat();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(this, "No legal rat classes!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // construct GUI components
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setSnapToTicks(true);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    mazeChooser.setMultiSelectionEnabled(false);
    mazeChooser.addChoosableFileFilter(new ExtensionFilter(".maz", "Maze files (*.maz)"));
    
    // event listeners
    cbox.addActionListener(this);
    slider.addChangeListener(this);
    
    // layout
    JPanel north1 = new JPanel();
    north1.add(new JLabel("Speed (FPS): "));
    north1.add(slider);
    
    JPanel north2 = new JPanel();
    north2.setAlignmentX(1.0f);
    north2.add(new JLabel("Rat: "));
    north2.add(cbox);
    
    Box north = Box.createVerticalBox();
    north.add(north1);
    north.add(north2);

    JPanel south = new JPanel();
    addButton(south, "Load Maze...", 'L');
    addButton(south, "Start", 'S');
    addButton(south, "Stop", 'T');
    addButton(south, "Reset", 'R');
    
    JPanel cp = new JPanel(new BorderLayout());
    cp.add(north, BorderLayout.NORTH);
    cp.add(ratpanel, BorderLayout.CENTER);
    cp.add(south, BorderLayout.SOUTH);
    setContentPane(cp);

    pack();
    center();
  }
  
  /** Responds to button presses, box clicks, and timer ticks in this window. */
  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();
    String command = ("" + event.getActionCommand()).intern();
    
    if (source == cbox)
    {
      // initiated by combo box; load rat
      ratClass = (Class)cbox.getSelectedItem();
      timer.stop();

      try
      {
        loadRat();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(this, "Unable to create rat!\n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    else if (source == timer)
    {
      // make rat move through maze
      if (maze != null)
      {
        boolean result = maze.update();
        
        if (maze.ratHasEscaped())
        {
          Animal rat = maze.getRat();
          JOptionPane.showMessageDialog(this, rat.getName() + " escaped in " + rat.getNumMoves() + " moves.");
          timer.stop();
        }
      }
    }
    else if (command == "Load Maze...")
    {
      // browse for file
      timer.stop();
      int result = mazeChooser.showOpenDialog(this);
      if (result != JFileChooser.APPROVE_OPTION)
        return;
      
      File f = mazeChooser.getSelectedFile();
      if (f == null)
        return;
      
      // attempt to load maze from file
      try 
      {
        maze = Maze.create(f.toString());
        ratpanel.resize();
      }
      catch (IOException ioe)
      {
        JOptionPane.showMessageDialog(this, "Unable to read maze!\n\n" + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      
      if (ratClass == null)
      {
        JOptionPane.showMessageDialog(this, "No rat type selected!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      
      // attempt to load rat object
      try
      {
        loadRat();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(this, "Unable to create rat!\n\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      
      // layout
      JPanel cp = (JPanel)getContentPane();
      cp.revalidate();
      
      pack();
      center();
    }
    else if (command == "Start")
    {
      // start timer
      if (!timer.isRunning()  &&  !maze.ratHasEscaped())
        timer.start();
    }
    else if (command == "Stop")
    {
      // kill timer
      timer.stop();
    }
    else if (command == "Reset")
    {
      // kill timer, and reset maze/rat
      timer.stop();
      if (maze != null)
        maze.reset();
    }
    
    // refresh view
    ratpanel.repaint();
  }
  
  /** Responds to slider sliding in this window. */
  public void stateChanged(ChangeEvent event)
  {
    FPS = slider.getValue();
    timer.setDelay(1000 / Math.max(1, FPS));
  }
  
  /* Loads rat object from rat class. */
  private final void loadRat() throws Exception
  {
    if (ratClass != null  &&  maze != null)
    {
      Constructor ctor = ratClass.getConstructor(new Class[] {});
      Animal rat = (Animal)ctor.newInstance(new Object[] {});
      maze.setRat(rat);
    }
  }
  
  /* Loads all rat classes in ratrace.rats using reflection. */
  private final void loadRatClasses()
  {
    // find rat classes
    ClassLoader cl = ClassLoader.getSystemClassLoader();
    String classpath = System.getProperty("java.class.path");
    StringTokenizer st = new StringTokenizer(classpath, ";");
    while (st.hasMoreTokens())
    {
      String path = st.nextToken();
      File ratDir = new File(path ); //    add    + "/ratrace/rats/" to the path variable IF rats are in subpackage
      if (!ratDir.exists())
        continue;
      File[] classes = ratDir.listFiles(new ExtensionFilter(".class", "Java class files (*.class)"));
      if (classes == null)
        continue;
      
      for (int ii = 0;  ii < classes.length;  ii++)
      {
        if (classes[ii].isDirectory())
          continue;
        String fileName = classes[ii].getName();
        String className = fileName.substring(0, fileName.lastIndexOf(".class"));
        try
        {
          // load all non-abstract classes in ratrace.rats package
          Class ratClass = cl.loadClass(className); //  add prefix "ratrace.rats." + IF rats are in their own package
          if ((ratClass.getModifiers() & Modifier.ABSTRACT) == 0)
            ratTypes.add(ratClass);
        }
        catch (ClassNotFoundException cnfe)
        {
          System.out.println("Bad class: " + cnfe.getMessage());
        }
      }
    }
  }
  
  /* Puts a new button in a panel. */
  private final void addButton(JPanel panel, String text, char mnemonic)
  {
    JButton butt = new JButton(text);
    butt.setMnemonic(mnemonic);
    butt.addActionListener(this);
    panel.add(butt);
  }

  /* Centers window on screen. */
  private final void center()
  {
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension scr = tk.getScreenSize();
    Dimension me  = this.getSize();
    setLocation((scr.width - me.width) / 2, (scr.height - me.height) / 2);
  }
  
  
  /* A drawing surface on which the rat maze is shown. */
  private final class RatPanel extends JPanel
  {
    // INSTANCE VARIABLES
    private final int FONT_SIZE = 12;
    private int colWidth = 12, rowHeight = 12;
    private Font FONT = new Font("Monospaced", Font.PLAIN, FONT_SIZE);
    private Font FONT2 = new Font("Monospaced", Font.BOLD, FONT_SIZE);
    
    /** Constructs a new panel. */
    public RatPanel()
    {
      setPreferredSize(new Dimension(400, 300));
      setBackground(Color.white);
    }
    
    /** Paints this panel on screen. */
    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      
      if (maze == null)
        return;
      
      // calculate sizes
      int numRows = maze.getNumRows();
      int numCols = maze.getNumColumns();
      colWidth  = this.getWidth()  / maze.getNumColumns();
      rowHeight = this.getHeight() / (maze.getNumRows() + 2);
      int fontSize = (int)Math.min(colWidth, rowHeight);
      if (FONT.getSize() != fontSize)
      {
        FONT  = new Font("Monospaced", Font.PLAIN, fontSize);
        FONT2 = new Font("Monospaced", Font.BOLD, fontSize);
      }
      
      // draw maze
      for (int row = 0;  row < numRows;  row++)
        for (int col = 0;  col < numCols;  col++)
        {
          int x = (int)(col * colWidth);
          int y = (int)(row * rowHeight);
          char square = maze.getSquare(row, col);
          if (square == Maze.FINISH)
          {
            g2.setPaint(Color.red);
            g2.setFont(FONT2);
            drawLetter(g2, square, row, col);
            g2.setFont(FONT);
          }
          else if (square == Maze.WALL)
          {
            g2.setPaint(Color.blue.darker());
            g2.fill3DRect(x, y, (int)colWidth, (int)rowHeight, true);
          }
        }
      
      // draw rat
      Animal rat = maze.getRat();
      if (rat == null)
        return;
      g2.setFont(FONT2);
      g2.setPaint(Color.magenta.darker());
      int row = rat.getRow();
      int col = rat.getColumn();
      int x = (int)(col * colWidth);
      int y = (int)(row * rowHeight);
      g2.fillOval(x, y, (int)colWidth, (int)rowHeight);

      x += 2;
      y += (int)(rowHeight - 2);
      g2.setPaint(Color.black);
      drawLetter(g2, rat.getLetter(), row, col);
      
      // draw # moves
      g2.setFont(FONT);
      drawString(g2, "Moves: " + rat.getNumMoves(), numRows + 1, 0);
    }
    
    /* Draws given letter in given row/col on screen. */
    private final void drawLetter(Graphics2D g2, char letter, int row, int col)
    {
      int x = (int)(col * colWidth + 2);
      int y = (int)((row + 1) * rowHeight - 2);
      g2.drawString("" + letter, x, y);
    }
    
    /* Draws given entire string at given starting row/col on screen. */
    private final void drawString(Graphics2D g2, String str, int row, int col)
    {
      for (int ii = 0;  ii < str.length();  ii++)
      {
        drawLetter(g2, str.charAt(ii), row, col + ii);
      }
    }
    
    /* Makes panel change preferred size when maze changes. */
    final void resize()
    {
      if (maze != null)
      {
        setPreferredSize(new Dimension(maze.getNumColumns() * FONT_SIZE + 2, (maze.getNumRows() + 2) * FONT_SIZE + 2));
        repaint();
      }
    }
  }
  
  /* File name filter used when loading mazes. */
  private static final class ExtensionFilter extends javax.swing.filechooser.FileFilter
    implements java.io.FilenameFilter
  {
    private String ext, desc;
    
    /** Constructs a new filter for the given extension (example: ".gif") and 
     *  description (example: "GIF Images (*.gif)").
     */
    public ExtensionFilter(String ext, String desc) {
      this.ext  = ext.toLowerCase();
      this.desc = desc;
    }
    
    /** Returns whether this filter will accept the given file; case-insensitive. */
    public boolean accept(File f)
    {
      return f != null  &&
        (f.isDirectory()  ||  f.getName().toLowerCase().endsWith(ext));
    }
    
    /** Returns whether this filter will accept the given file in the given
     *  directory; case-insensitive.
     */
    public boolean accept(File dir, String name)
    {
      return accept(new File(dir, name));
    }
    
    /** Returns a description of this filter. */
    public String getDescription()
    {
      return desc;
    }
  }
}