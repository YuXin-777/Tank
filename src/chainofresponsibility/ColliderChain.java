package chainofresponsibility;

import com.xy.tank.AbstractGameObject;
import com.xy.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain {
    private List<Collider> colliders;

    public ColliderChain() {
        initColliders();
    }
    private void initColliders() {
        colliders = new ArrayList<>();
        String[] collidersString = PropertyMgr.get("colliders").split(",");
        for(String name : collidersString){
            try {
                Class  clazz = Class.forName("chainofresponsibility." + name);
                Collider c = (Collider)(clazz.getConstructor().newInstance());
                colliders.add(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        for(Collider collider : colliders) {
            if(!collider.collide(go1, go2)) {
                return false;
            }
        }
        return true;
    }
}
