import data
import show
import keras
from keras import metrics
from keras.models import Model
from keras.layers import regularizers,Input,Conv2D,Dense,MaxPooling2D,Flatten
from keras.utils import np_utils
(X_train, y_train), (X_test, y_test),nb_classes = data.eachFile("/home/lailai/public/dataset-image2d-mnist-small-10k",28,28,1,0.8)
X_train = X_train.astype('float32')
X_test = X_test.astype('float32')
X_train /= 255
X_test /= 255
Y_train = np_utils.to_categorical(y_train, nb_classes)
Y_test = np_utils.to_categorical(y_test, nb_classes)
Input_0 = Input(shape=(28,28,1))
Conv2D_1 = Conv2D(32,(3,3),activation='relu')(Input_0)
MaxPooling2D_2 = MaxPooling2D()(Conv2D_1)
Conv2D_3 = Conv2D(32,(3,3),activation='relu')(MaxPooling2D_2)
MaxPooling2D_4 = MaxPooling2D()(Conv2D_3)
Flatten_5 = Flatten()(MaxPooling2D_4)
Dense_6 = Dense(10,activation='relu')(Flatten_5)
model = Model(inputs=[Input_0],outputs=[Dense_6])
compile = model.compile(loss='mean_squared_error',optimizer='Nadam',metrics=['accuracy'])
fit = model.fit(X_train,Y_train,batch_size=40,epochs=5,validation_data=(X_test, Y_test),verbose=1)
model.save('/home/lailai/model/1/qq.h5')
evaluate = model.evaluate(X_train,Y_train,verbose=1)
