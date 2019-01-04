package com.lucaci32u4.model.library.libgates;

import com.lucaci32u4.core.Logic;
import com.lucaci32u4.core.LogicComponent;
import com.lucaci32u4.core.LogicPin;
import com.lucaci32u4.model.library.LibComponent;
import com.lucaci32u4.model.library.LibFactory;
import com.lucaci32u4.model.parts.Component;
import com.lucaci32u4.ui.viewport.renderer.RenderAPI;
import com.lucaci32u4.ui.viewport.renderer.brush.Brush;

import javax.swing.*;
import java.util.UUID;

public class LibGates implements LibFactory {
	private static final UUID LIB_UUID = UUID.randomUUID();
	private static final String LIB_NAME = "Gates";
	private static final String[] LIB_COMPONENTS = new String[] { "AND" , "OR", "NOT" };
	
	@Override
	public UUID getFamilyUUID() {
		return LIB_UUID;
	}
	
	@Override
	public String getFamilyName() {
		return LIB_NAME;
	}
	
	@Override
	public String[] getComponentsName() {
		return LIB_COMPONENTS.clone();
	}
	
	@Override
	public Icon getComponentIcon(String name) {
		return null;
	}
	
	@Override
	public LibComponent createComponent(String name) {
		switch (name) {
			case "AND":
				return new GateAnd();
			case "OR":
				return new GateOr();
			case "NOT":
				return new GateNot();
			default:
				throw new IllegalArgumentException();
		}
	}
	static Brush gateOutlineBrush = null;
	static Brush gateInsideBrush = null;
	static boolean brushesReady = false;
}




class GateAnd implements LibComponent {
	private final Component.Termination[] arrayTerminations = new Component.Termination[3];
	private final LogicPin[] arrayPins = new LogicPin[3];
	private int posX = 0, posY = 0, width = 60, height = 60;

	@Override
	public void onAttach(Component componentContainer) {
		componentContainer.setWidth(width).setHeight(height);
		for (int i = 0; i < 3; i++) {
			arrayPins[i] = new LogicPin();
			arrayPins[i].setListening(i < 2);
			arrayPins[i].setDefaultValue(new Logic(Logic.LOW, false));
			arrayTerminations[i] = new Component.Termination(componentContainer, new LogicPin[]{ arrayPins[i]});
		}
	}
	
	@Override
	public Component.Termination[] getTerminations() {
		return arrayTerminations;
	}
	
	@Override
	public LogicPin[] onSimulationBegin() {
		return arrayPins;
	}
	
	@Override
	public void onSimulationSignalEvent() {
		Logic p1 = arrayPins[0].read();
		Logic p2 = arrayPins[1].read();
		arrayPins[2].drive(Logic.FULL_DRIVER, 1, p1.defined && p1.state && p2.defined && p2.state);
	}
	
	@Override
	public void onSimulationInterrupt(LogicComponent.Interrupter interrupter) {
		// Nothing: Gates do not take interrupts
	}
	
	@Override
	public void onSimulationEnd() {
		// Nothing: There are no resources allocated
	}
	
	@Override
	public void onChangePosition(int x, int y) {
		posX = x;
		posY = y;
		arrayTerminations[0].setConnectPosiiton(x, y + (height) / 3);
		arrayTerminations[1].setConnectPosiiton(x, y + (height * 2) / 3);
		arrayTerminations[2].setConnectPosiiton(x + width, y + height / 2);
	}
	
	@Override
	public void onInteractiveClick(int x, int y, boolean begin, boolean end) {
		// Nothing: Gates do not interact.
	}
	
	@Override
	public void onDraw(RenderAPI api) {
		if (!LibGates.brushesReady) {
			LibGates.gateOutlineBrush = api.createOutlineBrush(0, 0, 0);
			LibGates.gateInsideBrush = api.createSolidBrush(200, 200, 200);
			LibGates.brushesReady = true;
		}
		api.setBrush(LibGates.gateOutlineBrush);
		api.drawRectangle(posX, posY, posX + width, posY + height);
		api.setBrush(LibGates.gateInsideBrush);
		api.drawRectangle(posX + 1, posY + 1, posX + width - 1, posY + height - 1);
		api.setBrush(LibGates.gateOutlineBrush);
		api.drawText(posX + 20, posY + 20, "AND");
	}
	
	@Override
	public void onDetach() {
	
	}
}

class GateOr implements LibComponent {
	private final Component.Termination[] arrayTerminations = new Component.Termination[3];
	private final LogicPin[] arrayPins = new LogicPin[3];
	private int posX = 0, posY = 0, width = 60, height = 60;
	
	@Override
	public void onAttach(Component componentContainer) {
		componentContainer.setWidth(width).setHeight(height);
		for (int i = 0; i < 3; i++) {
			arrayPins[i] = new LogicPin();
			arrayPins[i].setListening(i < 2);
			arrayPins[i].setDefaultValue(new Logic(Logic.LOW, false));
			arrayTerminations[i] = new Component.Termination(componentContainer, new LogicPin[]{ arrayPins[i]});
		}
	}
	
	@Override
	public Component.Termination[] getTerminations() {
		return arrayTerminations;
	}
	
	@Override
	public LogicPin[] onSimulationBegin() {
		return arrayPins;
	}
	
	@Override
	public void onSimulationSignalEvent() {
		Logic p1 = arrayPins[0].read();
		Logic p2 = arrayPins[1].read();
		arrayPins[2].drive(Logic.FULL_DRIVER, 1, (p1.defined && p1.state) || (p2.defined && p2.state));
	}
	
	@Override
	public void onSimulationInterrupt(LogicComponent.Interrupter interrupter) {
		// Nothing: Gates do not take interrupts
	}
	
	@Override
	public void onSimulationEnd() {
		// Nothing: There are no resources allocated
	}
	
	@Override
	public void onChangePosition(int x, int y) {
		posX = x;
		posY = y;
		arrayTerminations[0].setConnectPosiiton(x, y + (height) / 3);
		arrayTerminations[1].setConnectPosiiton(x, y + (height * 2) / 3);
		arrayTerminations[2].setConnectPosiiton(x + width, y + height / 2);
	}
	
	@Override
	public void onInteractiveClick(int x, int y, boolean begin, boolean end) {
		// Nothing: Gates do not interact.
	}
	
	@Override
	public void onDraw(RenderAPI api) {
		if (!LibGates.brushesReady) {
			LibGates.gateOutlineBrush = api.createOutlineBrush(0, 0, 0);
			LibGates.gateInsideBrush = api.createSolidBrush(200, 200, 200);
			LibGates.brushesReady = true;
		}
		api.setBrush(LibGates.gateOutlineBrush);
		api.drawRectangle(posX, posY, posX + width, posY + height);
		api.setBrush(LibGates.gateInsideBrush);
		api.drawRectangle(posX + 1, posY + 1, posX + width - 1, posY + height - 1);
		api.setBrush(LibGates.gateOutlineBrush);
		api.drawText(posX + 20, posY + 20, "OR");
	}
	
	@Override
	public void onDetach() {
	
	}
}

class GateNot implements LibComponent {
	private final Component.Termination[] arrayTerminations = new Component.Termination[2];
	private final LogicPin[] arrayPins = new LogicPin[2];
	private int posX = 0, posY = 0, width = 30, height = 20;
	
	@Override
	public void onAttach(Component componentContainer) {
		componentContainer.setWidth(width).setHeight(height);
		for (int i = 0; i < 2; i++) {
			arrayPins[i] = new LogicPin();
			arrayPins[i].setListening(i < 1);
			arrayPins[i].setDefaultValue(new Logic(Logic.LOW, false));
			arrayTerminations[i] = new Component.Termination(componentContainer, new LogicPin[]{ arrayPins[i]});
		}
	}
	
	@Override
	public Component.Termination[] getTerminations() {
		return arrayTerminations;
	}
	
	@Override
	public LogicPin[] onSimulationBegin() {
		return arrayPins;
	}
	
	@Override
	public void onSimulationSignalEvent() {
		Logic p1 = arrayPins[0].read();
		arrayPins[1].drive(Logic.FULL_DRIVER, 1, !(p1.defined && p1.state));
	}
	
	@Override
	public void onSimulationInterrupt(LogicComponent.Interrupter interrupter) {
		// Nothing: Gates do not take interrupts
	}
	
	@Override
	public void onSimulationEnd() {
		// Nothing: There are no resources allocated
	}
	
	@Override
	public void onChangePosition(int x, int y) {
		posX = x;
		posY = y;
		arrayTerminations[0].setConnectPosiiton(x, y + height / 2);
		arrayTerminations[1].setConnectPosiiton(x + width, y + height / 2);
	}
	
	@Override
	public void onInteractiveClick(int x, int y, boolean begin, boolean end) {
		// Nothing: Gates do not interact.
	}
	
	@Override
	public void onDraw(RenderAPI api) {
		if (!LibGates.brushesReady) {
			LibGates.gateOutlineBrush = api.createOutlineBrush(0, 0, 0);
			LibGates.gateInsideBrush = api.createSolidBrush(200, 200, 200);
			LibGates.brushesReady = true;
		}
		api.setBrush(LibGates.gateOutlineBrush);
		api.drawRectangle(posX, posY, posX + width, posY + height);
		api.setBrush(LibGates.gateInsideBrush);
		api.drawRectangle(posX + 1, posY + 1, posX + width - 1, posY + height - 1);
	}
	
	@Override
	public void onDetach() {
	
	}
}

