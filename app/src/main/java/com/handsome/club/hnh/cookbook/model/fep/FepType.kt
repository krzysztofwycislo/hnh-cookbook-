package com.handsome.club.hnh.cookbook.model.fep


sealed class FepType {

    abstract val statReward: Int


    class Strength(
        override val statReward: Int,
    ) : FepType()

    class Agility(
        override val statReward: Int,
    ) : FepType()

    class Constitution(
        override val statReward: Int,
    ) : FepType()

    class Perception(
        override val statReward: Int,
    ) : FepType()

    class Dexterity(
        override val statReward: Int,
    ) : FepType()

    class Intelligence(
        override val statReward: Int,
    ) : FepType()

    class Charisma(
        override val statReward: Int,
    ) : FepType()

    class Will(
        override val statReward: Int,
    ) : FepType()

    class Psyche(
        override val statReward: Int,
    ) : FepType()

}