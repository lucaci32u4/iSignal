/*
 * iSignal - Digital circuit simulator
 * Copyright (C) 2018-present Iercosan-Lucaci Alexandru
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 *    ||=============================================||
 *    ||     _  _____  _          _            _     ||
 *    ||    (_)/  ___|(_)       =)_)-         | |    ||
 *    ||     _ \ `--.  _   __ _  _ __    __ _ | |    ||
 *    ||    | | `--. \| | / _` || '_ \  / _` || |    ||
 *    ||    | |/\__/ /| || (_| || | | || (_| || |    ||
 *    ||    |_|\____/ |_| \__, ||_| |_| \__,_||_|    ||
 *    ||                   __/ |                     ||
 *    ||                  |___/  Digital Simulator   ||
 *    ||=============================================||
 */

package com.lucaci32u4.ui.viewport.renderer;

import com.lucaci32u4.ui.viewport.renderer.brush.Brush;

public interface DrawAPI {
	int getSurfaceWidth();
	int getSurfaceHeight();
	float unitsToPixels(int units);
	float pixelsToUnits(int pixels);
	void setCanvasOffsetUnits(int offsetX, int offsetY);
	void setBrush(Brush brush);
	void drawLine(int fromX, int fromY, int toX, int toY, float thicknessPixels);
	void drawTriangle(int aX, int aY, int bX, int bY, int cX, int cY);
	void drawRectangle(int left, int top, int right, int bottom);
	void drawText(int left, int top, String text);
}
