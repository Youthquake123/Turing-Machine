package myCode;

import utm.Move;

/**
 * Head movements for all Turing Machine
 * @version 1.0
 */
public enum MoveGeneral implements Move {

    /** The right movement of a TM. */
    RIGHT,

    /** The left movement of a TM. */
    LEFT,

    /** The reset operation of a left-reset TM. */
    RESET;
}
