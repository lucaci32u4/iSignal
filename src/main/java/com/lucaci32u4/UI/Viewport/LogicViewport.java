package com.lucaci32u4.UI.Viewport;

import com.lucaci32u4.UI.Viewport.Picker.PickerAPI;
import com.lucaci32u4.UI.Viewport.Picker.PickerManager;
import com.lucaci32u4.UI.Viewport.Renderer.RenderAPI;
import com.lucaci32u4.UI.Viewport.Renderer.RenderManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LogicViewport {

	private RenderAPI pencil;
	private PickerAPI picker;
	private UserInputListener userInputListener;

	public void init(@NotNull JPanel displayPanel, @NotNull RenderAPI renderAPI, @NotNull PickerAPI pickerAPI, @NotNull UserInputListener inputListener) {
		pencil = renderAPI;
		picker = pickerAPI;
		userInputListener = inputListener;
		pencil.initRenderer(displayPanel, this);
		pencil.getCanvas().addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {

			}
			@Override public void mousePressed(MouseEvent e) {
				userInputListener.mouseButtonEvent(e);
			}
			@Override public void mouseReleased(MouseEvent e) {
				userInputListener.mouseButtonEvent(e);
			}
			@Override public void mouseEntered(MouseEvent e) {
				userInputListener.isInsidePerimeter(true);
			}
			@Override public void mouseExited(MouseEvent e) {
				userInputListener.isInsidePerimeter(false);
			}
		});
		pencil.getCanvas().addMouseMotionListener(new MouseMotionListener() {
			@Override public void mouseDragged(MouseEvent e) {
				userInputListener.mouseMotionEvent(e, true);
			}
			@Override public void mouseMoved(MouseEvent e) {
				userInputListener.mouseMotionEvent(e, false);
			}
		});
	}

	public PickerManager getPickerManager() {
		return picker;
	}

	public RenderManager getRenderManager() {
		return pencil;
	}

	public void destroy() {
		pencil.destroyRenderer();
		picker.destroy();
	}
	
	public void requestUpdate() {

	}
}