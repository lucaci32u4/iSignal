package com.lucaci32u4.model.parts;

import com.lucaci32u4.ui.viewport.renderer.DrawAPI;
import com.lucaci32u4.model.Subcurcuit;
import com.lucaci32u4.model.parts.wiring.Connectable;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;

import com.lucaci32u4.core.LogicPin;

import java.util.concurrent.atomic.AtomicBoolean;

public class Component {
	private final BehaviourSpecification spec;
	private @Getter long position = 0;
	private @Getter long dimension = 0;
	private @Getter boolean ghosting = false;
	private final AtomicBoolean selected = new AtomicBoolean();
	private Subcurcuit subcircuit = null;
	
	public Component(@NotNull BehaviourSpecification spec, Subcurcuit subcircuit) {
		this.spec = spec;
		this.subcircuit = subcircuit;
		spec.onAttach(this);
	}
	
	public void select(boolean isSel) {
		selected.set(isSel);
		invalidateGraphics();
	}
	
	public void move(long newPosition) {
		position = newPosition;
		spec.onChangePosition(newPosition);
		invalidateGraphics();
	}
	
	public void interact(long position, boolean begin, boolean end) {
		if (begin) selected.set(true);
		if (end) selected.set(false);
		spec.onInteractiveClick(position);
		invalidateGraphics();
	}
	
	public void invalidateGraphics() {
		subcircuit.invalidateGraphics();
	}
	
	public static class Termination implements Connectable {
		public static final int STATE_TRISTATE = 0;
		public static final int STATE_HIGH = 1;
		public static final int STATE_LOW = 2;
		public static final int STATE_CONFLICT = 3;
		public static final int STATE_MULTIBIT = 4;
		private @Setter int state;
		private @Setter long connectPosiiton;
		private final @Getter Component owner;
		private final @Getter int bitWidth;
		private final @Getter LogicPin[] pins;
		public Termination(@NotNull Component owner, @NotNull LogicPin[] pins) {
			this.owner = owner;
			this.pins = pins;
			bitWidth = pins.length;
		}
		void render(DrawAPI pencil, boolean attach, boolean detach) {
		
		}
		
		@Override public long getConnectPosition() {
			return connectPosiiton;
		}
		
		@Override public boolean isMutable() {
			return false;
		}
	}
	
	public interface BehaviourSpecification {
		void onAttach(Component componentContainer);
		void onChangePosition(long position);
		void onChangeDimension(long dimension);
		void onInteractiveClick(long position);
		Termination[] getTerminations();
	}
}