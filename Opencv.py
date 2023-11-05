import cv2

# Inicializa la cámara y los clasificadores
cap = cv2.VideoCapture(0)
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
eye_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_eye.xml')

frame_count = 0  # Contador de frames para guardar imágenes

while True:
    ret, frame = cap.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.3, minNeighbors=5)
    
    # Realiza la detección de ojos en las caras encontradas y dibuja rectángulos
    for (x, y, w, h) in faces:
        cv2.rectangle(frame, (x, y), (x + w, y + h), (255, 0, 0), 2)
        roi_gray = gray[y:y + h, x:x + w]
        roi_color = frame[y:y + h, x:x + w]
        eyes = eye_cascade.detectMultiScale(roi_gray, scaleFactor=1.1, minNeighbors=5)
        for (ex, ey, ew, eh) in eyes:
            cv2.rectangle(roi_color, (ex, ey), (ex + ew, ey + eh), (0, 255, 0), 2)
    
    cv2.imshow('Detección de Rostros y Ojos', frame)

    # Guarda la imagen cada 30 frames (puedes ajustar esto según tus necesidades)
    if frame_count % 30 == 0:
        image_filename = f'imagen_{frame_count}.jpg'
        cv2.imwrite(image_filename, frame)
        print(f'Guardada imagen: {image_filename}')
    
    frame_count += 1

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
