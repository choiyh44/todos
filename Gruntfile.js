module.exports = function(grunt) {
	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		watch : {
			scripts: {
			    files: ['src/main/webapp/jssrc/**/*.js'],
			    tasks: ['jshint', 'uglify']
			}
		},
		jshint : {
			all : ['Gruntfile.js', 'src/main/webapp/jssrc/**/*.js']
		},
		uglify: {
			options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
			},
			build: {
				files: [{
					expand: true,
					cwd: 'src/main/webapp/jssrc',
					src: '**/*.js',
					dest: 'src/main/webapp/js'
				}]
			}
		}
	});

	// Load the plugin that provides the "jshint" task.
	grunt.loadNpmTasks('grunt-contrib-jshint');

	// Load the plugin that provides the "uglify" task.
	grunt.loadNpmTasks('grunt-contrib-uglify');
	
	grunt.loadNpmTasks('grunt-contrib-watch');

	// Default task(s).
	grunt.registerTask('default', ['uglify', 'jshint', 'watch']);

};
