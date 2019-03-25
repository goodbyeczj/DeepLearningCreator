from keras.models import load_model
from keras.utils import np_utils
import data
(X_train, y_train), (X_test, y_test),nb_classes = data.eachFile("/home/lailai/public/dataset-image2d-mnist-small-10k",28,28,1,0.8)
X_train = X_train.astype('float32')
X_test = X_test.astype('float32')
X_train /= 255
X_test /= 255
Y_train = np_utils.to_categorical(y_train, nb_classes)
Y_test = np_utils.to_categorical(y_test, nb_classes)
model = load_model('/home/lailai/model/1/qq.h5')
model.predict(X_test)
