/*
Physics - a simple physics system for calculating forces
*/

// Vector2D - simple 2D vector
Vector2D{
	var <>x, <>y;

	*new{ | x=0, y=0 |
		^super.newCopyArgs(x,y);
	}

	* { | b |
		if(b.isKindOf(Vector2D))
		{
			^Vector2D(x*b.x,y*b.y);
		}
		{
			^Vector2D(x*b,y*b);
		}
	}

	/ { | b |
		if(b.isKindOf(Vector2D))
		{
			^Vector2D(x/b.x,y/b.y);
		}
		{
			^Vector2D(x/b,y/b);
		}
	}

	+ { | b |
		if(b.isKindOf(Vector2D))
		{
			^Vector2D(x+b.x,y+b.y);
		}
		{
			^Vector2D(x+b,y+b);
		}
	}

	- { | b |
		if(b.isKindOf(Vector2D))
		{
			^Vector2D(x-b.x,y-b.y);
		}
		{
			^Vector2D(x-b,y-b);
		}
	}

	normalize{
		var a, b;
		a = x / (x+y);
		b = y / (x+y);
		^Vector2D(a,b);
	}

	printOn{ | stream |
		stream << "Vector2D(" << x << "," << y << ")";
	}
}