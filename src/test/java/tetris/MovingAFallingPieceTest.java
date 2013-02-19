// Copyright (c) 2008-2012  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(NestedJUnit.class)
public class MovingAFallingPieceTest extends Assert {

    private static final int LOTS_OF_TIMES = 10;

    private final Board board = new Board(4, 8);


    public class A_falling_piece {

        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
            assertEquals("" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_be_moved_left() {
            board.moveLeft();

            assertEquals("" +
                    "...T....\n" +
                    "..TTT...\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_be_moved_right() {
            board.moveRight();

            assertEquals("" +
                    ".....T..\n" +
                    "....TTT.\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void can_be_moved_down() {
            board.moveDown();

            assertEquals("" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "........\n", board.toString());
        }

        @Test
        public void will_not_move_left_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveLeft();
            }

            assertEquals("" +
                    ".T......\n" +
                    "TTT.....\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void will_not_move_right_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveRight();
            }

            assertEquals("" +
                    "......T.\n" +
                    ".....TTT\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Ignore // TODO
        @Test
        public void will_not_move_down_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveDown();
            }

            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n", board.toString());
            assertFalse("should stop falling", board.hasFalling());
        }
    }


    // TODO: it can not be moved left if another piece is in the way
    // TODO: it can not be moved right if another piece is in the way
    // TODO: it can not be moved down if another piece is in the way (will stop falling)

    // P.S. Take into consideration, that part of the piece's area may be empty cells.
    // Only non-empty cells should take part in the collision checks.
}
