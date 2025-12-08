import { BrowserRouter, Navigate, Route, Routes } from 'react-router';
import Main from './pages/MainPage';
import Layout from './components/Layout';
import CoordinatesPage from './pages/CoordinatesPage';
import CoordinateCreatePage from './pages/CoordinateCreatePage';
import Providers from './components/Providers';
import CoordinateViewPage from './pages/CoordinateViewPage';
import CoordinateEditPage from './pages/CoordinateEditPage';
import VehicleCreatePage from './pages/VehicleCreatePage';
import VehicleViewPage from './pages/VehicleViewPage';
import EditVehiclePage from './pages/VehicleEditPage';
import SpecialVehicleActionsPage from './pages/SpecialVehicleActionsPage';

const App = () => {
  return (
    <Providers>
      <BrowserRouter>
        <Routes>
          <Route element={<Layout />}>
            <Route path="*" element={<Navigate to={'/'} />} />
            <Route index element={<Main />} />
            <Route path="/coordinates" element={<CoordinatesPage />} />
            <Route
              path="/coordinates/create"
              element={<CoordinateCreatePage />}
            />
            <Route path="/coordinates/:id" element={<CoordinateViewPage />} />
            <Route
              path="/coordinates/:id/edit"
              element={<CoordinateEditPage />}
            />
            <Route path="/vehicles/create" element={<VehicleCreatePage />} />
            <Route path="/vehicles/:id" element={<VehicleViewPage />} />
            <Route path="/vehicles/:id/edit" element={<EditVehiclePage />} />
            <Route path="/specials" element={<SpecialVehicleActionsPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Providers>
  );
};

export default App;
