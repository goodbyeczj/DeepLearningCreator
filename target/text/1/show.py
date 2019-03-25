import keras
import logging
class LossHistory(keras.callbacks.Callback):
    def on_train_begin(self, logs={}):
        self.losses = {'batch': [], 'epoch': []}
        self.accuracy = {'batch': [], 'epoch': []}
        self.val_loss = {'batch': [], 'epoch': []}
        self.val_acc = {'batch': [], 'epoch': []}
        self.categorical_accuracy = {'batch': [], 'epoch': []}
        self.val_categorical_accuracy = {'batch': [], 'epoch': []}
        self.mean_absolute_error = {'batch': [], 'epoch': []}
        self.val_mean_absolute_error = {'batch': [], 'epoch': []}
        self.sparse_categorical_accuracy = {'batch': [], 'epoch': []}
        self.val_sparse_categorical_accuracy = {'batch': [], 'epoch': []}
        self.binary_accuracy = {'batch': [], 'epoch': []}
        self.val_binary_accuracy = {'batch': [], 'epoch': []}
        self.top_k_categorical_accuracy = {'batch': [], 'epoch': []}
        self.val_top_k_categorical_accuracy = {'batch': [], 'epoch': []}
        self.sparse_top_k_categorical_accuracy = {'batch': [], 'epoch': []}
        self.val_sparse_top_k_categorical_accuracy = {'batch': [], 'epoch': []}

    def on_epoch_end(self, batch, logs={}):
        self.losses['epoch'].append(logs.get('loss'))
        self.accuracy['epoch'].append(logs.get('acc'))
        self.val_loss['epoch'].append(logs.get('val_loss'))
        self.val_acc['epoch'].append(logs.get('val_acc'))
        self.categorical_accuracy['epoch'].append(logs.get('categorical_accuracy'))
        self.val_categorical_accuracy['epoch'].append(logs.get('val_categorical_accuracy'))
        self.mean_absolute_error['epoch'].append(logs.get('mean_absolute_error'))
        self.val_mean_absolute_error['epoch'].append(logs.get('acc'))
        self.sparse_categorical_accuracy['epoch'].append(logs.get("sparse_categorical_accuracy"))
        self.val_sparse_categorical_accuracy['epoch'].append(logs.get("val_sparse_categorical_accuracy"))
        self.binary_accuracy['epoch'].append(logs.get("binary_accuracy"))
        self.val_binary_accuracy['epoch'].append(logs.get("val_binary_accuracy"))
        self.top_k_categorical_accuracy['epoch'].append(logs.get("top_k_categorical_accuracy"))
        self.val_top_k_categorical_accuracy['epoch'].append(logs.get("val_top_k_categorical_accuracy"))
        self.sparse_top_k_categorical_accuracy['epoch'].append(logs.get("sparse_top_k_categorical_accuracy"))
        self.val_sparse_top_k_categorical_accuracy['epoch'].append(logs.get("val_sparse_top_k_categorical_accuracy"))
        if self.losses['epoch'][0] is not None:
            logging.error("loss"+str(self.losses['epoch']))
        if self.accuracy['epoch'][0] is not None:
            logging.error("acc"+str(self.accuracy['epoch']))
        if self.val_loss['epoch'][0] is not None:
            logging.error("val_l"+str(self.val_loss['epoch']))
        if self.val_acc['epoch'][0] is not None:
            logging.error("val_a" + str(self.val_acc['epoch']))
        if self.categorical_accuracy['epoch'][0] is not None:
            logging.error("val_categorical_accuracy" + str(self.categorical_accuracy['epoch']))
        if self.val_mean_absolute_error['epoch'][0] is not None:
            logging.error("val_mean_absolute_error" + str(self.val_mean_absolute_error['epoch']))
        if self.val_categorical_accuracy['epoch'][0] is not None:
            logging.error("val_categorical_accuracy" + str(self.val_categorical_accuracy['epoch']))
        if self.mean_absolute_error['epoch'][0] is not None:
            logging.error("mean_absolute_error" + str(self.mean_absolute_error['epoch']))
        if self.sparse_categorical_accuracy['epoch'][0] is not None:
            logging.error("sparse_categorical_accuracy"+str(self.sparse_categorical_accuracy['epoch']))
        if self.val_sparse_categorical_accuracy['epoch'][0] is not None:
            logging.error("val_sparse_categorical_accuracy"+str(self.val_sparse_categorical_accuracy['epoch']))
        if self.binary_accuracy['epoch'][0] is not None:
            logging.error("binary_accuracy"+str(self.binary_accuracy['epoch']))
        if self.val_binary_accuracy['epoch'][0] is not None:
            logging.error("val_binary_accuracy"+str(self.val_binary_accuracy['epoch']))
        if self.top_k_categorical_accuracy['epoch'][0] is not None:
            logging.error("top_k_categorical_accuracy"+str(self.top_k_categorical_accuracy['epoch']))
        if self.val_top_k_categorical_accuracy['epoch'][0] is not None:
            logging.error("val_top_k_categorical_accuracy"+str(self.val_top_k_categorical_accuracy['epoch']))
        if self.sparse_top_k_categorical_accuracy['epoch'][0] is not None:
            logging.error("sparse_top_k_categorical_accuracy"+str(self.sparse_top_k_categorical_accuracy['epoch']))
        if self.val_sparse_top_k_categorical_accuracy['epoch'][0] is not None:
            logging.error("val_sparse_top_k_categorical_accuracy"+str(self.val_sparse_top_k_categorical_accuracy['epoch']))