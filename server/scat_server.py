from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from flask_autoindex import AutoIndex

import logging
import subprocess

logger = logging.getLogger(__name__)
app = Flask(__name__)

@app.route('/upload')
def render_file():
   return render_template('upload.html')


def runcmd(filename, include_stderr=True):

    pcapfilename = filename + '.pcap'
    args = ['scat', '-t', 'sec', '-d', filename, '-F', pcapfilename]
    print (args)
    try:
        print('execute')
        print(filename)

        result = subprocess.check_output(
                args,
                stderr=subprocess.STDOUT
            ).decode('utf-8')
        print(result)
        return (0, result)
        # return 0
    except subprocess.CalledProcessError as e:
        logger.exception(e)
        return (e.returncode, '%s' % e.output.decode())
    except Exception as e:
        logger.exception(e)
        return (-1, '%s' % e)

@app.route('/fileUpload', methods = ['GET', 'POST'])
def upload_file():
   if request.method == 'POST':
        f = request.files['file']
        f.save(secure_filename(f.filename))
        runcmd(filename=f.filename)
        # AutoIndex(app, browse_root='.')
        return 'uploads success!'

if __name__ == '__main__':
   app.run(host='0.0.0.0', port=4080, debug = True)
