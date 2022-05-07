package entities

import javax.persistence.*

@Entity
@Table(name = "COUNTRIES", schema = "TUDOR", catalog = "")
open class COUNTRIESEntity {
    @Id
    @get:Column(name = "ID", nullable = false)
    var ID: java.math.BigInteger? = null

    @get:Basic
    @get:Column(name = "NAME", nullable = true)
    var NAME: String? = null

    @get:Basic
    @get:Column(name = "CODE", nullable = true)
    var CODE: String? = null

    @get:Basic
    @get:Column(name = "CONTINENT", nullable = true)
    var CONTINENT: String? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "ID = $ID " +
                    "NAME = $NAME " +
                    "CODE = $CODE " +
                    "CONTINENT = $CONTINENT " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as COUNTRIESEntity

        if (ID != other.ID) return false
        if (NAME != other.NAME) return false
        if (CODE != other.CODE) return false
        if (CONTINENT != other.CONTINENT) return false

        return true
    }

}

