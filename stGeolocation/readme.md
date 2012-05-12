#stGeolocation

由于phoneGap 本身提供的geolocation获取当前位置信息太慢(只使用GPS的方式)，因此写一个同时可以使用NETWORK_PROVIDER方式获取地理位置信息的plugin

##安装

* 将`.java`根据里面的`package`放到`src`目录下
* 在你的`index.html`文件中引入`.js`文件
* 在`res/xml/plugins.xml`中添加：

	`<plugin name="STGGeolocation" value="neekey.phonegap.plugin.stGeolocation.stGeolocation"/>`
	
* 添加权限：

`<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>`

