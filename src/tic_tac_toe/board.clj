(ns tic-tac-toe.board)

(defonce start-board [[1 2 3]
                      [4 5 6]
                      [7 8 9]])

(defn print-board [board]
  (doseq [row board]
    (println row)))

(defn winner? [rows]
  (or (some #(every? (fn [c] (= c \x)) %) rows)
      (some #(every? (fn [c] (= c \o)) %) rows)))

(defn transpose [m]
  (apply mapv vector m))

(defn game-won? [board]
  (let [columns (transpose board)
        diagonal (vec (map-indexed (fn [i row] (nth row i)) board))
        anti-diagonal (reverse (map-indexed (fn [i row] (nth row i)) (reverse board)))]
    (or (winner? board)
        (winner? columns)
        (winner? [diagonal])
        (winner? [anti-diagonal]))))

(defn board-full? [board]
  (every? char? (flatten board)))

(defn legal-move? [board move]
  (and (<= 1 move 9)
       (integer? (get-in board [(int (Math/ceil (quot (dec move) 3))) (mod (dec move) 3)]))))

(defn make-move [board move turn]
  (update-in board [(int (Math/ceil (quot (dec move) 3))) (mod (dec move) 3)] (constantly turn)))

(def next-turn {\x \o
                \o \x})

(defn get-move! [turn]
  (printf "%s's turn. Please select a square\n" turn)
  (println "___________")
  (Integer/parseInt (read-line)))

(defn run-game [start-board]
  (loop [board start-board
         turn \x]
    (print-board board)
    (cond
      (game-won? board) (format "%s is the WINNER" (next-turn turn))
      (board-full? board) "DRAW"
      :else (let [move (get-move! turn)]
              (if (legal-move? board move)
                (recur (make-move board move turn) (next-turn turn))
                (do (println "\nPlease select a valid square")
                    (recur board turn)))))))

(comment
  (run-game start-board)
  )
