package entities

import javax.persistence.*

@Entity
@Table(name = "CITIES", schema = "TUDOR", catalog = "")
open class CITIESEntity {
    @Id
    @get:Column(name = "ID", nullable = true)
    var ID: java.math.BigInteger? = null

    @get:Basic
    @get:Column(name = "COUNTRY", nullable = true)
    var COUNTRY: java.math.BigInteger? = null

    @get:Basic
    @get:Column(name = "NAME", nullable = true)
    var NAME: String? = null

    @get:Basic
    @get:Column(name = "CAPITAL", nullable = true)
    var CAPITAL: java.math.BigInteger? = null

    @get:Basic
    @get:Column(name = "LATITUDE", nullable = true)
    var LATITUDE: Int? = null

    @get:Basic
    @get:Column(name = "LONGITUDE", nullable = true)
    var LONGITUDE: Int? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "ID = $ID " +
                    "COUNTRY = $COUNTRY " +
                    "NAME = $NAME " +
                    "CAPITAL = $CAPITAL " +
                    "LATITUDE = $LATITUDE " +
                    "LONGITUDE = $LONGITUDE " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CITIESEntity

        if (ID != other.ID) return false
        if (COUNTRY != other.COUNTRY) return false
        if (NAME != other.NAME) return false
        if (CAPITAL != other.CAPITAL) return false
        if (LATITUDE != other.LATITUDE) return false
        if (LONGITUDE != other.LONGITUDE) return false

        return true
    }

}

