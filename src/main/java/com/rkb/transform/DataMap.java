package com.rkb.transform;

import java.util.HashMap;
import java.util.Map;

public class DataMap {
	
	
	/**
	 * 噪声层Noise
	 */
	//GaussianNoise层
	public static Map<String, String> getGaussianNoise() {
		Map<String, String> map = new HashMap<>();
		map.put("stddev", "");
		return map;
	}
	
	//GaussianDropout层
	public static Map<String, String> getGaussianDropout() {
		Map<String, String> map = new HashMap<>();
		map.put("rate", "");
		return map;
	}
	
	//AlphaDropout层
	public static Map<String, String> getAlphaDropout() {
		Map<String, String> map = new HashMap<>();
		map.put("rate", "");
		map.put("noise_shape", ".noise_shape=");
		map.put("seed", "seed=");
		return map;
	}
	
	
	
	
	
	/**
	 * （批）规范化BatchNormalization
	 */
	//BatchNormalization层
	public static Map<String, String> getBatchNormalization() {
		Map<String, String> map = new HashMap<>();
		map.put("axis", "axis=");
		map.put("momentum", "momentum=");
		map.put("epsilon", "epsilon=");
		map.put("center", "center=");
		map.put("scale", "scale=");
		map.put("beta_initializer", ".beta_initializer=");
		map.put("gamma_initializer", ".gamma_initializer=");
		map.put("moving_mean_initializer", ".moving_mean_initializer=");
		map.put("moving_variance_initializer", ".moving_variance_initializer=");
		map.put("beta_regularizer", ".beta_regularizer=");
		map.put("gamma_regularizer", ".gamma_regularizer=");
		map.put("beta_constraint", ".beta_constraint=");
		map.put("gamma_constraint", ".gamma_constraint=");
		return map;
	}
	
	
	
	
	
	
	/**
	 * 高级激活层Advanced Activation
	 */
	//LeakyReLU层
	public static Map<String, String> getLeakyReLU() {
		Map<String, String> map = new HashMap<>();
		map.put("alpha", "alpha=");
		return map;
	}
	
	
	//PReLU层
	public static Map<String, String> getPReLU() {
		Map<String, String> map = new HashMap<>();
		map.put("alpha_initializer", ".alpha_initializer=");
		map.put("alpha_regularizer", ".alpha_regularizer=");
		map.put("alpha_constraint", ".alpha_constraint=");
		map.put("shared_axes", ".shared_axes=");
		return map;
	}
	
	
	//ELU层
	public static Map<String, String> getELU() {
		Map<String, String> map = new HashMap<>();
		map.put("alpha", "alpha=");
		return map;
	}
	
	
	//ThresholdedReLU
	public static Map<String, String> getThresholdedReLU() {
		Map<String, String> map = new HashMap<>();
		map.put("theta", "theta=");
		return map;
	}
	
	
	
	
	
	/**
	 * 嵌入层 Embedding
	 */
	//Embedding层
	public static Map<String, String> getEmbedding() {
		Map<String, String> map = new HashMap<>();
		map.put("input_dim", "");
		map.put("output_dim", "");
		map.put("embeddings_initializer", ".embeddings_initializer=");
		map.put("embeddings_regularizer", ".embeddings_regularizer=");
		map.put("embeddings_constraint", ".embeddings_constraint=");
		map.put("mask_zero", "mask_zero=");
		map.put("input_length", ".input_length=");
		return map;
	}
	
	
	
	
	
	/**
	 * 循环层Recurrent
	 */
	//Recurrent层
	public static Map<String, String> getRecurrent() {
		Map<String, String> map = new HashMap<>();
		map.put("weights", "");
		map.put("return_sequences", "return_sequences=");
		map.put("go_backwards", "go_backwards=");
		map.put("stateful", "stateful=");
		map.put("unroll", "unroll=");
		map.put("implementation", "implementation=");
		map.put("input_dim", "input_dim=");
		map.put("input_length", "input_length=");
		return map;
	}
	
	
	//SimpleRNN层
	public static Map<String, String> getSimpleRNN() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//GRU层
	public static Map<String, String> getGRU() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//LSTM层
	public static Map<String, String> getLSTM() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		map.put("recurrent_activation", ".recurrent_activation=");
		return map;
	}
	
	
	//ConvLSTM2D层
	public static Map<String, String> getConvLSTM2D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("data_format", ".data_format=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("activation", ".activation=");
		map.put("recurrent_activation", ".recurrent_activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//SimpleRNNCell层
	public static Map<String, String> getSimpleRNNCell() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//GRUCell层
	public static Map<String, String> getGRUCell() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//LSTMCell层
	public static Map<String, String> getLSTMCell() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//StackedRNNCells层
	public static Map<String, String> getStackedRNNCells() {
		Map<String, String> map = new HashMap<>();
		map.put("cells", "");
		return map;
	}
	
	
	//CuDNNGRU层
	public static Map<String, String> getCuDNNGRU() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	//CuDNNLSTM层
	public static Map<String, String> getCuDNNLSTM() {
		Map<String, String> map = new HashMap<>();
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("recurrent_initializer", ".recurrent_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("recurrent_regularizer", ".recurrent_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("recurrent_constraints", ".recurrent_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("dropout", "dropout=");
		map.put("recurrent_dropout", "recurrent_dropout=");
		return map;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 局部连接层LocallyConnceted
	 */
	//LocallyConnected1D层
	public static Map<String, String> getLocallyConnected1D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		return map;
	}
	
	
	//LocallyConnected2D层
	public static Map<String, String> getLocallyConnected2D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		return map;
	}
	
	
	/**
	 * 池化层Pooling
	 */
	//MaxPooling1D层
	public static Map<String, String> getMaxPooling1D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		return map;
	}
	
	
	//MaxPooling2D层
	public static Map<String, String> getMaxPooling2D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//MaxPooling3D层
	public static Map<String, String> getMaxPooling3D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//AveragePooling1D层
	public static Map<String, String> getAveragePooling1D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		return map;
	}
	
	
	//AveragePooling2D层
	public static Map<String, String> getAveragePooling2D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("border_mode", ".border_mode=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//AveragePooling3D层
	public static Map<String, String> getAveragePooling3D() {
		Map<String, String> map = new HashMap<>();
		map.put("pool_size", "pool_size=");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("border_mode", ".border_mode=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//GlobalMaxPooling1D层
	public static Map<String, String> getGlobalMaxPooling1D() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	
	//GlobalAveragePooling1D层
	public static Map<String, String> getGlobalAveragePooling1D() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	//GlobalMaxPooling2D层
	public static Map<String, String> getGlobalMaxPooling2D() {
		Map<String, String> map = new HashMap<>();
		map.put("dim_ordering", ".dim_ordering=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//GlobalAveragePooling2D
	public static Map<String, String> getGlobalAveragePooling2D() {
		Map<String, String> map = new HashMap<>();
		map.put("dim_ordering", ".dim_ordering=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 卷积层Convolutional
	 */
	//Conv1D层
	public static Map<String, String> getConv1D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		return map;
	}
	
	
	//Conv2D层
	public static Map<String, String> getConv2D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("input_shape","input_shape=");
		map.put("border_mode",".border_mode=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//SeparableConv2D层
	public static Map<String, String> getSeparableConv2D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("data_format", ".data_format=");
		map.put("depth_multiplier", "depth_multiplier=");
		map.put("depthwise_regularizer", ".depthwise_regularizer=");
		map.put("pointwise_regularizer", ".pointwise_regularizer=");
		map.put("depthwise_constraint", ".depthwise_constraint=");
		map.put("pointwise_constraint", ".pointwise_constraint=");
		return map;
	}
	
	
	//Conv2DTranspose层
	public static Map<String, String> getConv2DTranspose() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//Conv3D层
	public static Map<String, String> getConv3D() {
		Map<String, String> map = new HashMap<>();
		map.put("filters", "");
		map.put("kernel_size", "");
		map.put("strides", "strides=");
		map.put("padding", ".padding=");
		map.put("activation", ".activation=");
		map.put("dilation_rate", "dilation_rate=");
		map.put("use_bias", "use_bias=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//Cropping1D层
	public static Map<String, String> getCropping1D() {
		Map<String, String> map = new HashMap<>();
		map.put("cropping", "cropping=");
		return map;
	}
	
	
	//Cropping2D层
	public static Map<String, String> getCropping2D() {
		Map<String, String> map = new HashMap<>();
		map.put("cropping", "cropping=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//Cropping3D层
	public static Map<String, String> getCropping3D() {
		Map<String, String> map = new HashMap<>();
		map.put("cropping", "cropping=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//UpSampling1D层
	public static Map<String, String> getUpSampling1D() {
		Map<String, String> map = new HashMap<>();
		map.put("size", "size=");
		return map;
	}
	
	
	//UpSampling2D层
	public static Map<String, String> getUpSampling2D() {
		Map<String, String> map = new HashMap<>();
		map.put("size", "size=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//UpSampling3D层
	public static Map<String, String> getUpSampling3D() {
		Map<String, String> map = new HashMap<>();
		map.put("size", "size=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//ZeroPadding1D层
	public static Map<String, String> getZeroPadding1D() {
		Map<String, String> map = new HashMap<>();
		map.put("padding", "padding=");
		return map;
	}
	
	
	//ZeroPadding2D层
	public static Map<String, String> getZeroPadding2D() {
		Map<String, String> map = new HashMap<>();
		map.put("padding", "padding=");
		map.put("data_format", ".data_format=");
		return map;
	}
	
	
	//ZeroPadding3D层
	public static Map<String, String> getZeroPadding3D() {
		Map<String, String> map = new HashMap<>();
		map.put("padding", "padding=");
		map.put("data_format", ".data_format=");
		return map;
	}
		
	
	
	
	
	
	
	
	
	/**
	 * 常用层
	 */
	//Dense层
	public static Map<String, String> getDense() {
		Map<String, String> map = new HashMap<>();
		map.put("in","");
		map.put("units", "");
		map.put("activation", ".activation=");
		map.put("use_bias", "use_bias=");
		map.put("input_shape","input_shape=");
		map.put("kernel_initializer", ".kernel_initializer=");
		map.put("bias_initializer", ".bias_initializer=");
		map.put("kernel_regularizer", ".kernel_regularizer=");
		map.put("bias_regularizer", ".bias_regularizer=");
		map.put("activity_regularizer", ".activity_regularizer=");
		map.put("kernel_constraints", ".kernel_constraints=");
		map.put("bias_constraints", ".bias_constraints=");
		return map;
	}
	
	
	//Activation层
	public static Map<String, String> getActivation() {
		Map<String, String> map = new HashMap<>();
		map.put("activation", "");
		return map;
	}
	
	
	//Dropout层
	public static Map<String, String> getDropout() {
		Map<String, String> map = new HashMap<>();
		map.put("rate", "");
		map.put("noise_shape", "noise_shape=");
		map.put("seed", "seed=");
		return map;
	}
	
	
	//Flatten层
	public static Map<String, String> getFlatten() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	//Reshape层
	public static Map<String, String> getReshape() {
		Map<String, String> map = new HashMap<>();
		map.put("target_shape", "");
		return map;
	}
	
	
	//Permute层
	public static Map<String, String> getPermute() {
		Map<String, String> map = new HashMap<>();
		map.put("dims", "");
		return map;
	}
	
	
	//RepeatVector层
	public static Map<String, String> getRepeatVector() {
		Map<String, String> map = new HashMap<>();
		map.put("n", "");
		return map;
	}
	
	
	//Lambda层
	public static Map<String, String> getLambda() {
		Map<String, String> map = new HashMap<>();
		map.put("function", "");
		map.put("output_shape", ".output_shape=");
		map.put("mask", ".mask=");
		map.put("arguments", ".arguments=");
		return map;
	}
	
	
	//ActivityRegularizer层
	public static Map<String, String> getActivityRegularizer() {
		Map<String, String> map = new HashMap<>();
		map.put("l1", "l1=");
		map.put("l2", "l2=");
		return map;
	}
	
	
	//Masking层
	public static Map<String, String> getMasking() {
		Map<String, String> map = new HashMap<>();
		map.put("mask_value", "mask_value=");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 包装器Wrapper
	 */
	//TimeDistributed包装器
	public static Map<String, String> getTimeDistributed() {
		Map<String, String> map = new HashMap<>();
		map.put("layer", "");
		return map;
	}
	
	
	//Bidirectional包装器
	public static Map<String, String> getBidirectional() {
		Map<String, String> map = new HashMap<>();
		map.put("layer", "");
		map.put("merge_mode", ".merge_mode=");
		return map;
	}
	
	
	
	
	
	
	
	
	/**
	 * 融合层Merge
	 */
	//Add 加
	public static Map<String, String> getAdd() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	//SubStract 减
	public static Map<String, String> getSubStract() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	//Multiply 乘
	public static Map<String, String> getMultiply() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
		
		
	//Average 平均
	public static Map<String, String> getAverage() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
		
		
	//Maximum 最大值
	public static Map<String, String> getMaximum() {
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	
	//Concatenate
	public static Map<String, String> getConcatenate() {
		Map<String, String> map = new HashMap<>();
		map.put("axis", "axis=");
		map.put("kwargs", "");
		return map;
	}
	
	
	//Dot
	public static Map<String, String> getDot() {
		Map<String, String> map = new HashMap<>();
		map.put("axes", "");
		map.put("**kwargs", "");
		map.put("normalize", "normalize=");
		return map;
	}
	
	
	//add 加
	public static Map<String, String> getadd() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
	
	
	//subStract 减
	public static Map<String, String> getsubStract() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
	
	
	//multiply 乘
	public static Map<String, String> getmultiply() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
		
		
	//average 平均
	public static Map<String, String> getaverage() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
		
		
	//maximum 最大值
	public static Map<String, String> getmaximum() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
	
	
	//concatenate
	public static Map<String, String> getconcatenate() {
		Map<String, String> map = new HashMap<>();
		map.put("axis", "axis=");
		map.put("inputs", "");
		map.put("**kwargs", "");
		return map;
	}
	
	
	//dot
	public static Map<String, String> getdot() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "");
		map.put("axes", "");
		map.put("**kwargs", "");
		map.put("normalize", "normalize=");
		return map;
	}
	public static Map<String, String> getfit() {
		Map<String, String> map = new HashMap<>();
		map.put("x", "");
		map.put("y", "");
		map.put("epochs", "epochs=");
		map.put("batch_size", "batch_size=");
		map.put("verbose","verbose=");
		map.put("validation_data","validation_data=");
		map.put("callbacks","callbacks=");
		return map;
	}

	public static Map<String, String> getcompile() {
		Map<String, String> map = new HashMap<>();
		map.put("loss", "loss=");
		map.put("optimizer", "optimizer=");
		map.put("metrics", "@metrics=");
		return map;
	}

	public static Map<String, String> getevaluate() {
		Map<String, String> map = new HashMap<>();
		map.put("x", "");
		map.put("y", "");
		map.put("batch_size", "batch_size=");
		map.put("verbose","verbose=");
		return map;
	}
	public static Map<String, String> getSGD() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put("momentum", "momentum=");
		map.put("decay", "decay=");
		map.put("nesterov", "nesterov=");
		return map;
	}
	public static Map<String, String> getRMSprop() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put("rho", "rho=");
		map.put("epsilon", "epsilon=");
		return map;
	}


	public static Map<String, String> getAdadelta() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put("rho", "rho=");
		map.put("epsilon", "epsilon=");
		return map;
	}

	public static Map<String, String> getAdam() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put(" beta_1", " beta_1=");
		map.put(" beta_2", " beta_2=");
		map.put("epsilon", "epsilon=");
		return map;
	}

	public static Map<String, String> getAdamax() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put(" beta_1", " beta_1=");
		map.put(" beta_2", " beta_2=");
		map.put("epsilon", "epsilon=");
		return map;
	}
	public static Map<String, String> getNadam() {
		Map<String, String> map = new HashMap<>();
		map.put("lr", "lr=");
		map.put(" beta_1", " beta_1=");
		map.put(" beta_2", " beta_2=");
		map.put("epsilon", "epsilon=");
		return map;
	}
	public static Map<String, String> getInput() {
		Map<String, String> map = new HashMap<>();
		map.put("shape", "shape=");
		return map;
	}
	public static Map<String, String> getModel() {
		Map<String, String> map = new HashMap<>();
		map.put("inputs", "@inputs=");
		map.put("outputs","@outputs=");
		return map;
	}
	public static Map<String, String> getMobileNetV2() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("alpha","alpha=");
		map.put("depth_multiplier","depth_multiplier=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getNASNet() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getDenseNet() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getMobileNet() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getInceptionResNetV2() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getInceptionV3() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getResNet50() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getVGG19() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getVGG16() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}
	public static Map<String, String> getXception() {
		Map<String, String> map = new HashMap<>();
		map.put("input_shape", "input_shape=");
		map.put("include_top","include_top=");
		map.put("weights","weights=");
		map.put("input_tensor","input_tensor=");
		map.put("classes","classes=");
		map.put("pooling","pooling=");
		return map;
	}


}
