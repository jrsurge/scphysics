/*
Physics - a simple physics system for calculating forces
*/

// PhysicsObject - simple object to apply forces to
PhysicsObject{
	var <>vecPos, <>width, <>height, <>mass, <>vecAccel, <>vecVel;

	*new{ | x=0, y=0, w=10, h=10,  mass=10 |
		^super.newCopyArgs(Vector2D(x,y), w, h, mass).init;
	}

	init{
		vecAccel = Vector2D(0.0,0.0);
		vecVel = Vector2D(0.0,0.0);
	}

	update{ |  worldBounds |
		vecVel = vecVel + vecAccel;
		vecPos = vecPos + vecVel;
		vecAccel = vecAccel * 0.0;
		this.checkBounds(worldBounds);
	}

	applyForce{ | vecForce |
		var accel;
		accel = vecForce / mass;
		vecAccel = vecAccel + accel;
	}

	checkBounds{ | worldBounds |
		//if outside bounds, invert velocity and reset position
		if( this.vecPos.x >= (worldBounds.width - (this.width/2.0)) )
		{
			this.vecVel.x = this.vecVel.x * -1.0;
			this.vecPos.x = worldBounds.width - (this.width/2.0);
		}
		{
			if( this.vecPos.x <= (0 + (this.width/2.0)) )
			{
				this.vecVel.x = this.vecVel.x * -1.0;
				this.vecPos.x = this.width/2.0;
			}
		};

		if( this.vecPos.y >= (worldBounds.height - (this.height/2.0)) )
		{
			this.vecVel.y = this.vecVel.y * -1.0;
			this.vecPos.y = worldBounds.height - (this.height/2.0);
		}
		{
			if( this.vecPos.y <= (0 + (this.height/2.0)) )
			{
				this.vecVel.y = this.vecVel.y * -1.0;
				this.vecPos.y = this.height/2.0;
			}
		}
	}

	printOn{ | stream |
		stream << "PhysicsObject[" << vecPos << "," << vecAccel << "," << vecVel << "," << mass << "]";
	}
}