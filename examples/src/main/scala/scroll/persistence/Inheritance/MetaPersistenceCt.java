package scroll.persistence.Inheritance;

import scroll.internal.Compartment;

import java.util.UUID;

public abstract class MetaPersistenceCt implements Compartment {

    /**
     * public, da man es mir Reflection eh jederzeit ändern könnte und vor allem, da es so einfacher geht.
     * Zudem gleich mit einem default Wert. Kann später natürlich noch überschrieben werden.
     */
    public UUID uuid_ = UUID.randomUUID();

}
