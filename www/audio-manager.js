var exec = require('cordova/exec');

function promiseExec(success, error, service, action, args) {
	return new Promise(function (resolve, reject) {
		exec(function (result) {
			resolve(result);
			if (success) success(result);
		}, function (result) {
			reject(result);
			if (error) error(result);
		}, service, action, args);
	});
}

exports.STREAM_VOICE_CALL = 0;
exports.STREAM_SYSTEM = 1;
exports.STREAM_RING = 2;
exports.STREAM_MUSIC = 3;
exports.STREAM_ALARM = 4;
exports.STREAM_NOTIFICATION = 5;
exports.STREAM_DTMF = 8;
exports.STREAM_ACCESSIBILITY = 10;

exports.FLAG_SHOW_UI = 1 << 0;
exports.FLAG_ALLOW_RINGER_MODES = 1 << 1;
exports.FLAG_PLAY_SOUND = 1 << 2;
exports.FLAG_REMOVE_SOUND_AND_VIBRATE = 1 << 3;
exports.FLAG_VIBRATE = 1 << 4;

exports.getStreamMaxVolume = function (streamType, success, error) {
	return promiseExec(success, error, 'audio-manager', 'getStreamMaxVolume', [streamType]);
};
exports.getStreamVolume = function (streamType, success, error) {
	return promiseExec(success, error, 'audio-manager', 'getStreamVolume', [streamType]);
};
exports.setStreamVolume = function (streamType, index, flugs, success, error) {
	return promiseExec(success, error, 'audio-manager', 'setStreamVolume', [streamType, index, flugs]);
};
