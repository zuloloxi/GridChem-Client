package nanocad.util;

/*
	A basic extension of the java.awt.Dialog class
 */

import java.awt.*;

public class newPDialog extends Dialog
{
	// Used for addNotify check.
	boolean fComponentsAdjusted = false;


	//{{DECLARE_CONTROLS
	java.awt.List list1;
	java.awt.Button button1;
	//}}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == newPDialog.this)
				Dialog1_WindowClosing(event);
		}
	}
	
	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == button1)
				button1_Action(event);
		}
	}

	class SymComponent extends java.awt.event.ComponentAdapter
	{
		public void componentShown(java.awt.event.ComponentEvent event)
		{
			Object object = event.getSource();
			if (object == newPDialog.this)
				Dialog1_ComponentShown(event);
		}
	}

	public newPDialog(Frame parent, String title, boolean modal)
	{
		this(parent, modal);
		setTitle(title);
	}
	public newPDialog(Frame parent, boolean modal)
	{
		super(parent, modal);

		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(insets().left + insets().right + 324,insets().top + insets().bottom + 386);
		list1 = new java.awt.List(0,false);
		add(list1);
		list1.setBounds(insets().left + 24,insets().top + 36,276,276);
		button1 = new java.awt.Button();
		button1.setLabel("OK");
		button1.setBounds(insets().left + 24,insets().top + 324,276,36);
		button1.setBackground(new Color(12632256));
		add(button1);
		setTitle("Choose Property");
		//}}

		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		button1.addActionListener(lSymAction);
		SymComponent aSymComponent = new SymComponent();
		this.addComponentListener(aSymComponent);
		//}}
	}
	public void addItem(String item)
	{
		int i = -1 ;
		boolean itemFound = false;
		int val = Integer.parseInt(item.substring(0,item.indexOf(" ")));
	    while(i+1<list1.getItemCount())
	    {
	        i++;
	        String lItem = list1.getItem(i);
	        int compVal = Integer.parseInt(lItem.substring(0,lItem.indexOf(" ")));
	        if (val < compVal)
	        {
	            list1.addItem(item,i);
	            return;
	        }	        
	    }
	    list1.addItem(item);
		return;
	}
	public void addNotify()
	{
  	    // Record the size of the window prior to calling parents addNotify.
	    Dimension d = getSize();

		super.addNotify();

		if (fComponentsAdjusted)
			return;

		// Adjust components according to the insets
		setSize(insets().left + insets().right + d.width, insets().top + insets().bottom + d.height);
		Component components[] = getComponents();
		for (int i = 0; i < components.length; i++)
		{
			Point p = components[i].getLocation();
			p.translate(insets().left, insets().top);
			components[i].setLocation(p);
		}
		fComponentsAdjusted = true;
	}
	void button1_Action(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		// Hide the Dialog
		setVisible(false);
		//}}
	}
	void Dialog1_ComponentShown(java.awt.event.ComponentEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		// Select the specified item...
		list1.select(0);
		//}}
	}
	void Dialog1_WindowClosing(java.awt.event.WindowEvent event)
	{
		hide();
	}
	public synchronized void show()
	{
		Rectangle bounds = getParent().bounds();
		Rectangle abounds = bounds();

		move(bounds.x + (bounds.width - abounds.width)/ 2,
			 bounds.y + (bounds.height - abounds.height)/2);

		super.show();
	}
}
