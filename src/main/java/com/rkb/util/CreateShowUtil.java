package com.rkb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-1-15 上午10:56
 */
@Component
public class CreateShowUtil {
    String core = "import keras\n" +
            "import logging\n" +
            "class LossHistory(keras.callbacks.Callback):\n" +
            "    def on_train_begin(self, logs={}):\n" +
            "        self.losses = {'batch': [], 'epoch': []}\n" +
            "        self.accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_loss = {'batch': [], 'epoch': []}\n" +
            "        self.val_acc = {'batch': [], 'epoch': []}\n" +
            "        self.categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.mean_absolute_error = {'batch': [], 'epoch': []}\n" +
            "        self.val_mean_absolute_error = {'batch': [], 'epoch': []}\n" +
            "        self.sparse_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_sparse_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.binary_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_binary_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.top_k_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_top_k_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.sparse_top_k_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "        self.val_sparse_top_k_categorical_accuracy = {'batch': [], 'epoch': []}\n" +
            "\n" +
            "    def on_epoch_end(self, batch, logs={}):\n" +
            "        self.losses['epoch'].append(logs.get('loss'))\n" +
            "        self.accuracy['epoch'].append(logs.get('acc'))\n" +
            "        self.val_loss['epoch'].append(logs.get('val_loss'))\n" +
            "        self.val_acc['epoch'].append(logs.get('val_acc'))\n" +
            "        self.categorical_accuracy['epoch'].append(logs.get('categorical_accuracy'))\n" +
            "        self.val_categorical_accuracy['epoch'].append(logs.get('val_categorical_accuracy'))\n" +
            "        self.mean_absolute_error['epoch'].append(logs.get('mean_absolute_error'))\n" +
            "        self.val_mean_absolute_error['epoch'].append(logs.get('acc'))\n" +
            "        self.sparse_categorical_accuracy['epoch'].append(logs.get(\"sparse_categorical_accuracy\"))\n" +
            "        self.val_sparse_categorical_accuracy['epoch'].append(logs.get(\"val_sparse_categorical_accuracy\"))\n" +
            "        self.binary_accuracy['epoch'].append(logs.get(\"binary_accuracy\"))\n" +
            "        self.val_binary_accuracy['epoch'].append(logs.get(\"val_binary_accuracy\"))\n" +
            "        self.top_k_categorical_accuracy['epoch'].append(logs.get(\"top_k_categorical_accuracy\"))\n" +
            "        self.val_top_k_categorical_accuracy['epoch'].append(logs.get(\"val_top_k_categorical_accuracy\"))\n" +
            "        self.sparse_top_k_categorical_accuracy['epoch'].append(logs.get(\"sparse_top_k_categorical_accuracy\"))\n" +
            "        self.val_sparse_top_k_categorical_accuracy['epoch'].append(logs.get(\"val_sparse_top_k_categorical_accuracy\"))\n" +
            "        if self.losses['epoch'][0] is not None:\n" +
            "            logging.error(\"loss\"+str(self.losses['epoch']))\n" +
            "        if self.accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"acc\"+str(self.accuracy['epoch']))\n" +
            "        if self.val_loss['epoch'][0] is not None:\n" +
            "            logging.error(\"val_l\"+str(self.val_loss['epoch']))\n" +
            "        if self.val_acc['epoch'][0] is not None:\n" +
            "            logging.error(\"val_a\" + str(self.val_acc['epoch']))\n" +
            "        if self.categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_categorical_accuracy\" + str(self.categorical_accuracy['epoch']))\n" +
            "        if self.val_mean_absolute_error['epoch'][0] is not None:\n" +
            "            logging.error(\"val_mean_absolute_error\" + str(self.val_mean_absolute_error['epoch']))\n" +
            "        if self.val_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_categorical_accuracy\" + str(self.val_categorical_accuracy['epoch']))\n" +
            "        if self.mean_absolute_error['epoch'][0] is not None:\n" +
            "            logging.error(\"mean_absolute_error\" + str(self.mean_absolute_error['epoch']))\n" +
            "        if self.sparse_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"sparse_categorical_accuracy\"+str(self.sparse_categorical_accuracy['epoch']))\n" +
            "        if self.val_sparse_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_sparse_categorical_accuracy\"+str(self.val_sparse_categorical_accuracy['epoch']))\n" +
            "        if self.binary_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"binary_accuracy\"+str(self.binary_accuracy['epoch']))\n" +
            "        if self.val_binary_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_binary_accuracy\"+str(self.val_binary_accuracy['epoch']))\n" +
            "        if self.top_k_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"top_k_categorical_accuracy\"+str(self.top_k_categorical_accuracy['epoch']))\n" +
            "        if self.val_top_k_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_top_k_categorical_accuracy\"+str(self.val_top_k_categorical_accuracy['epoch']))\n" +
            "        if self.sparse_top_k_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"sparse_top_k_categorical_accuracy\"+str(self.sparse_top_k_categorical_accuracy['epoch']))\n" +
            "        if self.val_sparse_top_k_categorical_accuracy['epoch'][0] is not None:\n" +
            "            logging.error(\"val_sparse_top_k_categorical_accuracy\"+str(self.val_sparse_top_k_categorical_accuracy['epoch']))";
    @Autowired
    FileUtil fileUtil;
//    String PATH = "/home/lailai/Testing";
    String PATH = PathUtil.getText();
    public String getCore(Long id) {
        fileUtil.write(getCore(),PATH+"/"+id.toString() +"/show.py");
        return getCore();
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }
}
