(ns uxbox.state.images)

(defn assoc-collection
  "A reduce function for assoc the image collection
  to the state map."
  [state coll]
  (let [id (:id coll)]
    (assoc-in state [:images-by-id id] coll)))

(defn dissoc-collection
  "A reduce function for dissoc the image collection
  to the state map."
  [state id]
  (update state :images-by-id dissoc id))

(defn select-first-collection
  "A reduce function for select the first image collection
  to the state map."
  [state]
  (let [colls (sort-by :id (vals (:images-by-id state)))]
    (assoc-in state [:dashboard :collection-id] (:id (first colls)))))

(defn dissoc-image
  "A reduce function for dissoc the image collection
  to the state map."
  [state coll-id image]
  (let [images (get-in state [:images-by-id coll-id :images])
        images (filterv #(not= (:id image) (:id %)) images)]
    (assoc-in state [:images-by-id coll-id images] images)))