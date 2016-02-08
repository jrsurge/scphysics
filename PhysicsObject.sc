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

	update{ |  worldBounds |
		vecVel = vecVel + vecAccel;
		vecPos = vecPos + vecVel;
		this.checkBounds(worldBounds);
		vecAccel = vecAccel * 0;
	}

	applyForce{ | vecForce |
		var accel;
		accel = vecForce / mass;
		vecAccel = vecAccel + accel;
	}

	checkBounds{ | worldBounds |
		//if outside bounds, invert velocity and reset position
		if( this.vecPos.x > worldBounds.width )
		{
			this.vecVel.x = this.vecVel.x * -1;
			this.vecPos.x = worldBounds.width;
		};
		if( this.vecPos.x < 0 )
		{
			this.vecVel.x = this.vecVel.x * -1;
			this.vecPos.x = 0;
		};

		if( this.vecPos.y > worldBounds.height )
		{
			this.vecVel.y = this.vecVel.y * -1;
			this.vecPos.y = worldBounds.height;
		};

		if( this.vecPos.y < 0 )
		{
			this.vecVel.y = this.vecVel.y * -1;
			this.vecPos.y = 0;
		};
	}

	printOn{ | stream |
		stream << "PhysicsObject[" << vecPos << "," << vecAccel << "," << vecVel << "," << mass << "]";
	}
}