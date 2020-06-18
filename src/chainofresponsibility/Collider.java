package chainofresponsibility;

import com.xy.tank.AbstractGameObject;

public interface Collider {
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
