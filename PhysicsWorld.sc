/*
Physics - a simple physics system for calculating forces
*/

// PhysicsWorld - simple world for applying Vector2D forces to PhysicsObjects
PhysicsWorld{
	var <>bounds, <>gravity, forces, <objects;

	*new{ | bounds, vecGravity |
		^super.newCopyArgs(bounds, vecGravity).init;
	}

	init{
		forces = List();
		forces.add(gravity);
		objects = List();
	}

	addForce{ | vecForce |
		if(vecForce.isKindOf(Vector2D))
		{
			forces.add(vecForce);
		}
		{
			forces.add(Vector2D(vecForce));
		}
	}

	// if not given an object, create a random one in the world
	addObject{ | obj |
		if(obj.isKindOf(PhysicsObject))
		{
			objects.add(obj)
		}
		{
			objects.add(PhysicsObject(rrand(0,bounds.width),rrand(0,bounds.height),rrand(0.01,10)));
		}
	}

	simulate{
		// apply every force to every object in the world, and update
		forces.do({ | force |
			objects.do({ | object |
				object.applyForce(force);
				object.update(bounds);
			});
		});
	}
}