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

package com.lucaci32u4.UI.Viewport.Renderer.Brushes;

public class SolidBrush extends Brush {
	public static final SolidBrush BACKGROUND 	= new SolidBrush(null);
	public static final SolidBrush PATTERN 		= new SolidBrush(null);
	public static final SolidBrush TRISTATE 	= new SolidBrush(null);
	public static final SolidBrush LOW 			= new SolidBrush(null);
	public static final SolidBrush HIGH 		= new SolidBrush(null);
	public static final SolidBrush ERROR 		= new SolidBrush(null);
	public SolidBrush(Object obj) {
		super(BrushType.COLOR, obj);
	}
}
