/*
Physics - a simple physics system for calculating forces
*/

// PhysicsObject - simple object to apply forces to
PhysicsObject{
	var <>vecPos, <>mass, <>vecAccel, <>vecVel;

	*new{ | x=0, y=0,  mass=10 |
		^super.newCopyArgs(Vector2D(x,y),mass).init;
	}

	init{
		vecAccel = Vector2D(0,0);
		vecVel = Vector2D(0,0);
	}

	update{
		vecVel = vecVel + vecAccel;
		vecPos = vecPos + vecVel;
		vecAccel = vecAccel * 0;
	}

	applyForce{ | vecForce |
		var accel;
		accel = vecForce / mass;
		vecAccel = vecAccel + accel;
	}

	printOn{ | stream |
		stream << "PhysicsObject[" << vecPos << "," << vecAccel << "," << vecVel << "," << mass << "]";
	}
}