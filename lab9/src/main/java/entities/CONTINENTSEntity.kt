package entities

import javax.persistence.*

@Entity
@Table(name = "CONTINENTS", schema = "TUDOR", catalog = "")
open class CONTINENTSEntity {
    @Id
    @get:Column(name = "ID", nullable = true)
    var ID: java.math.BigInteger? = null

    @get:Basic
    @get:Column(name = "NAME", nullable = true)
    var NAME: String? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "ID = $ID " +
                    "NAME = $NAME " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CONTINENTSEntity

        if (ID != other.ID) return false
        if (NAME != other.NAME) return false

        return true
    }

}

