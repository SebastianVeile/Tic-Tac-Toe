(ns tic-tac-toe.game-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.board :as game]))

(deftest column-winner-test
  (testing "Testing X wins in first column"
    (is (game/game-won? [[\x 2 3]
                         [\x 5 6]
                         [\x 8 9]])))

  (testing "Testing X wins in second column"
    (is (game/game-won? [[1 \x 3]
                         [3 \x 6]
                         [7 \x 9]])))

  (testing "Testing X wins in third column"
    (is (game/game-won? [[1 2 \x]
                         [3 4 \x]
                         [7 8 \x]])))

  (testing "Testing O wins in first column"
    (is (game/game-won? [[\o 2 3]
                         [\o 5 6]
                         [\o 8 9]])))

  (testing "Testing X wins in second column"
    (is (game/game-won? [[1 \o 3]
                         [4 \o 6]
                         [7 \o 9]])))

  (testing "Testing X wins in third column"
    (is (game/game-won? [[1 2 \o]
                         [4 5 \o]
                         [7 8 \o]]))))

(deftest row-winner-test
  (testing "Testing X wins in first row"
    (is (game/game-won? [[\x \x \x]
                         [4 5 6]
                         [7 8 9]])))

  (testing "Testing X wins in second row"
    (is (game/game-won? [[1 2 3]
                         [\x \x \x]
                         [7 8 9]])))

  (testing "Testing X wins in third row"
    (is (game/game-won? [[1 2 3]
                         [4 5 6]
                         [\x \x \x]])))

  (testing "Testing O wins in first row"
    (is (game/game-won? [[\o \o \o]
                         [4 5 6]
                         [7 8 9]])))

  (testing "Testing X wins in second row"
    (is (game/game-won? [[1 2 3]
                         [\o \o \o]
                         [7 8 9]])))

  (testing "Testing X wins in third row"
    (is (game/game-won? [[1 2 3]
                         [4 5 6]
                         [\o \o \o]]))))

(deftest diagonal-win-test
  (testing "Testing X wins in diagonal"
    (is (game/game-won? [[1 2 \x]
                         [4 \x 6]
                         [\x 8 9]])))

  (testing "Testing O wins in Diagonal"
    (is (game/game-won? [[1 2 \o]
                         [4 \o 6]
                         [\o 8 9]])))

  (testing "Testing X wins anti-diagonal"
    (is (game/game-won? [[\x 2 3]
                         [4 \x 6]
                         [7 8 \x]])))

  (testing "Testing O wins anti-diagonal"
    (is (game/game-won? [[\o 2 3]
                         [4 \o 6]
                         [7 8 \o]]))))

(deftest testing-draw-on-full-board
  (let [board [[\o \o \x]
               [\x \x \o]
               [\o \x \x]]]
    (is (and (not (game/game-won? board))
             (game/board-full? board)
             true))))